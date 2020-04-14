package com.bing.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 蓝兵 on 2019/9/24.
 */

public class RabbitMQUtil {

    private static final RabbitMQUtil ourInstance = new RabbitMQUtil();
    private volatile static Connection connection;

    static RabbitMQUtil getInstance() {
        return ourInstance;
    }

    private RabbitMQUtil() {
        try {
            getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("创建连接失败");
        }
    }

    private static synchronized Connection getConnection() throws IOException, TimeoutException {
        if (connection == null) {
            //连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            //连接5672端口  注意15672为工具界面端口  25672为集群端口
            factory.setPort(5672);
            //factory.setVirtualHost("/");
            factory.setUsername("guest");
            factory.setPassword("guest");
            //获取连接
            connection = factory.newConnection();
        }
        return connection;
    }

    public static Channel createDeclareChannel(String queue, String exchange, BuiltinExchangeType exchangeType,
            String routingKey, boolean durable) {
        return createDeclareChannel(queue, exchange, exchangeType, routingKey, false, durable, false);
    }

    /**
     * 注意！！！RabbitMQ 只能保证同一个queue里面的消息被 [绑定在同一个queue的不同消费者] 消费一次
     * (就是同一个queue绑定了两个消费者)，不能保证消息被路由到多个queue后仍然只被消费一次，
     * 需要我们注意交换机类型及路由键的使用
     *
     * @param queue        队列名
     * @param exchange     交换机名
     * @param exchangeType 交换机类型
     * @param routingKey   路由键, 交换机在direct模式下, 路由键重复意味着同一个消息会被路由到不同的queue,
     *                     造成多次消费, 需要注意这种情况是否被业务允许, topic 和 fanout 模式, 本身就允许
     *                     消息路由到多个queue, 让多个消费者消费
     * @param exclusive    是否排外  即只允许该连接(Connection)【而不是 channel】下的消费者, 绑定该队列,
     *                     但是不限制发送消息方的 channel 和 connection
     * @param durable      是否持久化
     * @param autoDelete   是否自动删除  消费完，并且消费者断开连接后自动删除，全部消息过期，不会自动删除
     */
    public static Channel createDeclareChannel(String queue, String exchange, BuiltinExchangeType exchangeType,
            String routingKey, boolean exclusive, boolean durable, boolean autoDelete) {
        try {
            Channel channel = getConnection().createChannel();
            channel.queueDeclare(queue, durable, exclusive, autoDelete, null);
            channel.exchangeDeclare(exchange, exchangeType, durable, autoDelete, null);
            channel.queueBind(queue, exchange, routingKey);
            return channel;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("创建通道失败");
        }
    }

    public static Channel createChannel() throws IOException, TimeoutException {
        return getConnection().createChannel();
    }

    public static synchronized void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }
}

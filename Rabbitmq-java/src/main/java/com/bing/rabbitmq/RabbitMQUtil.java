package com.bing.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 蓝兵 on 2019/9/24.
 */

public class RabbitMQUtil {

    public final static String QUEUE_SIMPLE = "queue_simple";
    public final static String EXCHANGE_SIMPLE = "exchange_simple";
    public final static String ROUTING_KEY_SIMPLE = "routing_key_simple";

    private static final RabbitMQUtil ourInstance = new RabbitMQUtil();
    private volatile static Connection connection;

    static RabbitMQUtil getInstance() {
        return ourInstance;
    }

    private RabbitMQUtil() {
        try {
            getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
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

    public static Channel createDeclareChannel(String queue, String exchange, String routingKey) throws IOException, TimeoutException {
        Channel channel = getConnection().createChannel();
        /**
         *  队列名
         *  是否持久化
         *  是否排外  即只允许该channel访问该队列   一般等于true的话用于一个队列只能有一个消费者来消费的场景
         *  是否自动删除  消费完删除
         *  其他属性
         */
        channel.queueDeclare(queue, true, false, false, null);
        channel.exchangeDeclare(exchange, "direct", true, false, null);
        channel.queueBind(queue, exchange, routingKey);
        return channel;
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

package com.bing.rabbitmq.rpc;

import com.bing.rabbitmq.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * Created by 蓝兵 on 2019/9/27.
 */
public class Client {

    public static void main(String[] args) throws TimeoutException, IOException {
        Channel channel = RabbitMQUtil.createChannel();
        // 预先定义响应的结果，即预先订阅响应结果的队列，先订阅响应队列，再发送消息到请求队列
        String replay_to_queue = channel.queueDeclare().getQueue();
        final String correlationId = UUID.randomUUID().toString();
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                if (properties.getCorrelationId().equals(correlationId)) {
                    String message = new String(body, "UTF-8");
                    System.out.println("已接收到服务器的响应结果：" + message);
                }
            }
        };
        channel.basicConsume(replay_to_queue, true, consumer);

        // 将消息发送到请求队列中
        String rpc_queue = "rpc_queue";
        String message = "Hello RabbitMQ";
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().correlationId(correlationId).replyTo(replay_to_queue).build();
        channel.basicPublish("", rpc_queue, properties, message.getBytes("UTF-8"));
        System.out.println("已发出请求请求消息：" + message);
    }
}

package com.bing.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 蓝兵 on 2019/9/23.
 */

public class Receiver {

    public static void main(String[] args) throws TimeoutException {
        //获取连接
        try {
            //获取通道
            //Channel channel = RabbitMQUtil.createChannel(RabbitMQUtil.QUEUE_SIMPLE,
            //        RabbitMQUtil.EXCHANGE_SIMPLE, RabbitMQUtil.ROUTING_KEY_SIMPLE);

            Channel channel = RabbitMQUtil.createChannel();
            DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                        AMQP.BasicProperties properties, byte[] body) throws IOException {

                    String message = new String(body, "UTF-8");
                    System.out.println("收到消息 [" + message + "]");
                }
            };

            channel.basicConsume(RabbitMQUtil.QUEUE_SIMPLE, true, consumer);

        } catch (IOException e) {
            e.printStackTrace();
            RabbitMQUtil.closeConnection();
        }
    }
}
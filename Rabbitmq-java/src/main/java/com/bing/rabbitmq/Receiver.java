package com.bing.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by 蓝兵 on 2019/9/23.
 */

public class Receiver {

    public static void main(String[] args) {
        //获取连接
        try {
            //获取通道
            //Channel channel = RabbitMQUtil.createChannel(RabbitMQUtil.QUEUE_SIMPLE,
            //        RabbitMQUtil.EXCHANGE_SIMPLE, RabbitMQUtil.ROUTING_KEY_SIMPLE);

            Channel channel = RabbitMQUtil.createChannel();
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(RabbitMQUtil.QUEUE_SIMPLE, true, consumer);

            while (true) {
                //该方法会阻塞
                System.out.println(" ---- ");
                QueueingConsumer.Delivery delivery = null;
                try {
                    delivery = consumer.nextDelivery();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String message = new String(delivery.getBody());
                System.out.println("收到消息 [" + message + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
            RabbitMQUtil.closeConnection();
        }
    }
}
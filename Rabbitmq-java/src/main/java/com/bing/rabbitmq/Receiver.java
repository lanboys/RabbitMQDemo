package com.bing.rabbitmq;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 蓝兵 on 2019/9/23.
 */

public class Receiver {

    public static void main(String[] args) throws TimeoutException {
        create(DeclareExchangeQueue.queue_direct);
    }

    public static void create(String queue) throws TimeoutException {
        //获取连接
        try {
            //获取通道
            final Channel channel = RabbitMQUtil.createChannel();
            channel.basicQos(1);
            channel.basicConsume(queue, true, new DeliverCallback() {
                public void handle(String consumerTag, Delivery delivery) throws IOException {
                    String message = new String(delivery.getBody(), "UTF-8");
                    System.out.println("收到消息 [" + message + "]");

                    // 手动应答
                    //Envelope envelope = delivery.getEnvelope();
                    //channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }, new CancelCallback() {
                public void handle(String consumerTag) throws IOException {

                }
            });

            //DefaultConsumer consumer = new DefaultConsumer(channel) {
            //    @Override
            //    public void handleDelivery(String consumerTag, Envelope envelope,
            //            AMQP.BasicProperties properties, byte[] body) throws IOException {
            //        String message = new String(body, "UTF-8");
            //        System.out.println("收到消息 [" + message + "]");
            //    }
            //};
            //channel.basicConsume(queue, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
            RabbitMQUtil.closeConnection();
        }
    }
}
package com.bing.rabbitmq;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * Created by 蓝兵 on 2019/9/23.
 * <p>
 * https://blog.csdn.net/qq_33040219/article/details/82383127
 */

public class Sender {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建通道
        //Channel channel = RabbitMQUtil.createDeclareChannel(RabbitMQUtil.QUEUE_SIMPLE,
        //        RabbitMQUtil.EXCHANGE_SIMPLE, RabbitMQUtil.ROUTING_KEY_SIMPLE);
        Channel channel = RabbitMQUtil.createChannel();

        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String message = "我是来自星星的大佬 " + new Date().toLocaleString();
            //发布消息
            //channel.basicPublish("", RabbitMQUtil.QUEUE_SIMPLE, null, message.getBytes());

            channel.basicPublish(RabbitMQUtil.EXCHANGE_SIMPLE, RabbitMQUtil.ROUTING_KEY_SIMPLE, null, message.getBytes());
            System.out.println("发布消息 [" + message + "]");
        }
        //最后关闭通关和连接
        channel.close();
        RabbitMQUtil.closeConnection();
    }
}

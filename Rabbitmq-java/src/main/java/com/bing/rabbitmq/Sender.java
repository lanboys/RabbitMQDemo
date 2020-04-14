package com.bing.rabbitmq;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * Created by 蓝兵 on 2019/9/23.
 * <p>
 * https://blog.csdn.net/qq_33040219/article/details/82383127
 * <p>
 * https://mp.weixin.qq.com/s?__biz=MzIyNDU2NTc5Mw==&mid=2247484235&idx=1&sn=03ee8d17356d19db3b27e8e9987f9e51&chksm=e80c4c05df7bc5139a06c9d58fd9d101a9e887462a070169562c68f12af538503f1b885c3b14&mpshare=1&scene=24&srcid=0414qBuwMkCaNpNNCmZSEgi8&sharer_sharetime=1586842986872&sharer_shareid=b1c53f31f416bd89f024af8a3167959d#rd
 */

public class Sender {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建通道
        Channel channel = RabbitMQUtil.createChannel();
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String exchange = DeclareExchangeQueue.exchange_direct;
            String routingKey = DeclareExchangeQueue.routing_key_direct;
            String message = exchange + " ## " + routingKey + ", 我是来自星星的大佬 " + i + " , " + new Date().toLocaleString();
            //发布消息
            channel.basicPublish(exchange, routingKey, null, message.getBytes());
            System.out.println("发布消息 [" + message + "]");

            String exchange1 = DeclareExchangeQueue.exchange_fanout;
            String routingKey1 = DeclareExchangeQueue.routing_key_fanout;
            String message1 = exchange1 + " ## " + routingKey1 + ", 我是来自星星的大佬 " + i + " , " + new Date().toLocaleString();
            channel.basicPublish(exchange1, routingKey1, null, message1.getBytes());
            System.out.println("发布消息 [" + message1 + "]");
        }
        //最后关闭通关和连接
        channel.close();
        RabbitMQUtil.closeConnection();
    }
}

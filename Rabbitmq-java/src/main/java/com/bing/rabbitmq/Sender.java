package com.bing.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * Created by 蓝兵 on 2019/9/23.
 * <p>
 * https://blog.csdn.net/qq_33040219/article/details/82383127
 */

public class Sender {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException {
        //创建连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();

        //声明队列
        /**
         *  队列名
         *  是否持久化
         *  是否排外  即只允许该channel访问该队列   一般等于true的话用于一个队列只能有一个消费者来消费的场景
         *  是否自动删除  消费完删除
         *  其他属性
         *
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //消息内容
        /**
         * 交换机
         * 队列名
         * 其他属性  路由
         * 消息body
         */
        String message = "队列消息内容";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" Sent '" + message + "'");

        //最后关闭通关和连接
        channel.close();
        connection.close();
    }
}

package com.bing.lan.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by 蓝兵 on 2019/9/30.
 */
public class MessageConsumer implements MessageListener {

    public void onMessage(Message message) {
        System.out.println("Received:" + message);
    }
}

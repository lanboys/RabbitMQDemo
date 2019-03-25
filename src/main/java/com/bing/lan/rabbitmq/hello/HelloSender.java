package com.bing.lan.rabbitmq.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.bing.lan.rabbitmq.RabbitConfig.HELLO_QUEUES;

/**
 * @author lan_bing
 * @date 2019-03-25 10:08
 */
@Component
public class HelloSender {

    @Autowired
    AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "队列 hello " + new Date();
        System.out.println("Sender : " + context);
        rabbitTemplate.convertAndSend(HELLO_QUEUES, context);
    }
}
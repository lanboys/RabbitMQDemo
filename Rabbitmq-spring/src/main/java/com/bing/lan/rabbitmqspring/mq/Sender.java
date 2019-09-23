package com.bing.lan.rabbitmqspring.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.bing.lan.rabbitmqspring.RabbitConfig.BING_QUEUES;
import static com.bing.lan.rabbitmqspring.RabbitConfig.HELLO_QUEUES;

/**
 * @author lan_bing
 * @date 2019-03-25 10:08
 */
@Component
public class Sender {

    @Autowired
    AmqpTemplate rabbitTemplate;

    public void helloSend(String context) {
        System.out.println("队列 hello Sender : " + context);
        rabbitTemplate.convertAndSend(HELLO_QUEUES, context);
    }

    public void bingSend(Bing context) {
        System.out.println("队列 bing Sender : " + context);
        rabbitTemplate.convertAndSend(BING_QUEUES, context);
    }
}
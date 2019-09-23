package com.bing.lan.rabbitmqspring.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.bing.lan.rabbitmqspring.RabbitConfig.BING_QUEUES;
import static com.bing.lan.rabbitmqspring.RabbitConfig.HELLO_QUEUES;

/**
 * @author lan_bing
 * @date 2019-03-25 10:09
 */
@Component
public class Receiver {

    @RabbitListener(queues = HELLO_QUEUES)
    public void helloProcess(String hello) {
        System.out.println("hello receiver : " + hello);
    }

    @RabbitListener(queues = BING_QUEUES)
    public void bingProcess(Bing bing) {
        System.out.println("bing receiver : " + bing);
    }
}
package com.bing.lan.rabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.bing.lan.rabbitmq.RabbitConfig.HELLO_QUEUES;

/**
 * @author lan_bing
 * @date 2019-03-25 10:09
 */
@Component
@RabbitListener(queues = HELLO_QUEUES)
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }
}
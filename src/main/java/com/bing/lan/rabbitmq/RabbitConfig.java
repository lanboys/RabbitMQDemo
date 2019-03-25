package com.bing.lan.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lan_bing
 * @date 2019-03-25 10:12
 */
@Configuration
public class RabbitConfig {

    public static final String HELLO_QUEUES = "hello";

    @Bean
    public Queue helloQueue() {
        return new Queue(HELLO_QUEUES);
    }
}
package com.bing.lan.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lan_bing
 * @date 2019-03-25 10:12
 */
@Configuration
//@EnableRabbit
public class RabbitConfig /*implements RabbitListenerConfigurer */ {

    public static final String HELLO_QUEUES = "hello";

    public static final String BING_QUEUES = "bing";

    //@Autowired
    //ConnectionFactory connectionFactory;
    //
    //@Bean
    //public RabbitTemplate rabbitTemplate() {
    //final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    //rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    //    return rabbitTemplate;
    //}

    //@Override
    //public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
    //DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
    //factory.setMessageConverter(new MappingJackson2MessageConverter());
    //
    //registrar.setMessageHandlerMethodFactory(factory);
    //}

    @Bean
    public Queue helloQueue() {
        return new Queue(HELLO_QUEUES);
    }

    @Bean
    public Queue bingQueue() {
        return new Queue(BING_QUEUES);
    }
}
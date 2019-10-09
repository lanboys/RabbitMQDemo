package com.bing.lan.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by 蓝兵 on 2019/10/8.
 */
@Component
public class RabbitListenerReceiver {

    private static Logger logger = LoggerFactory.getLogger(RabbitListenerReceiver.class);

    //@RabbitHandler
    @RabbitListener(queues = "queueTest", containerFactory = "rabbitListenerContainerFactory")
    public void receive(Map message) {
        logger.info("收到消息2：{}", message);
    }
}

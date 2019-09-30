package com.bing.lan.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by 蓝兵 on 2019/9/30.
 */
public class MessageConsumer implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    public void onMessage(Message message) {
        logger.info("收到消息：{}", message);
    }
}

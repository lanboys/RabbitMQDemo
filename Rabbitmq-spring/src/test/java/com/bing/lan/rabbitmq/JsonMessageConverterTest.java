package com.bing.lan.rabbitmq;

import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.JsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 蓝兵 on 2019/10/7.
 */

public class JsonMessageConverterTest {

    @Test
    public void converter() {

        Map<String, Object> log = new HashMap<String, Object>();
        log.put("level", "info");
        log.put("operateId", 666);
        log.put("msg", "修改密码，修改前密码：123456，修改后密码：111111");

        JsonMessageConverter converter = new JsonMessageConverter();
        Message message = converter.toMessage(log, new MessageProperties());

        System.out.println("converter(): " + message);
    }
}

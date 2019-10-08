package com.bing.lan.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 蓝兵 on 2019/9/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringRabbitTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendMessage() {
        Map<String, Object> log = new HashMap<String, Object>();
        log.put("level", "info");
        log.put("timestamp", new Date());
        log.put("operateId", 666);
        log.put("msg", "修改密码，修改前密码：123456，修改后密码：111111");

        amqpTemplate.convertAndSend("queueTestKey", log);
    }
}
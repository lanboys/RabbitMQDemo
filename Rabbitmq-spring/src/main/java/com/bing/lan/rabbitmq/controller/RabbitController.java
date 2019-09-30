package com.bing.lan.rabbitmq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 蓝兵 on 2019/9/30.
 */
@Controller
public class RabbitController {

    private static Logger logger = LoggerFactory.getLogger(RabbitController.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/")
    @ResponseBody
    public String rabbit() {
        Map<String, Object> log = new HashMap<String, Object>();
        log.put("level", "info");
        log.put("timestamp", new Date());
        log.put("operateId", 666);
        log.put("msg", "修改密码，修改前密码：123456，修改后密码：111111");

        amqpTemplate.convertAndSend("queueTestKey", log);
        logger.info("发送消息：{}", log);
        return new Date().toLocaleString() + " 发送消息成功：" + log;
    }
}

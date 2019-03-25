package com.bing.lan.rabbitmq;

import com.bing.lan.rabbitmq.mq.Bing;
import com.bing.lan.rabbitmq.mq.Sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Autowired
    Sender sender;

    @Test
    public void hello() throws Exception {
        sender.helloSend("hello " + new Date().toLocaleString());
        sender.bingSend(new Bing());
    }
}
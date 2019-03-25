package com.bing.lan.rabbitmq;

import com.bing.lan.rabbitmq.hello.HelloSender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Autowired
    HelloSender sender;

    @Test
    public void hello() throws Exception {
        sender.send();
    }
}
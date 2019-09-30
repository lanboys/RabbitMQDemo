package com.bing.lan.rabbitmq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by 蓝兵 on 2019/9/30.
 */
@Controller
public class RabbitController {

    @RequestMapping("/")
    @ResponseBody
    public String rabbit() {
        return "hello rabbit " + new Date();
    }
}

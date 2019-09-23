package com.bing.lan.rabbitmqspring.mq;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lan_bing
 * @date 2019-03-25 11:25
 */
public class Bing implements Serializable {

    private String name = "bing_name";
    private int age = 14;
    private Date date = new Date();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Bing{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}

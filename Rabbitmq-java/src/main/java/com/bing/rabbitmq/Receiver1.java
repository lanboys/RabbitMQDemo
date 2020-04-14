package com.bing.rabbitmq;

import java.util.concurrent.TimeoutException;

/**
 * Created by 蓝兵 on 2019/9/23.
 */

public class Receiver1 {

    public static void main(String[] args) throws TimeoutException {
         Receiver.create(DeclareExchangeQueue.queue_direct);
    }
}
package com.bing.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;

/**
 * Created by lb on 2020/4/14.
 */

public class DeclareExchangeQueue {

    public final static String queue_direct = "queue_direct";
    public final static String exchange_direct = "exchange_direct";
    public final static String routing_key_direct = "routing_key_direct";

    public final static String queue_fanout_1 = "queue_fanout_1";
    public final static String queue_fanout_2 = "queue_fanout_2";
    public final static String exchange_fanout = "exchange_fanout";
    public final static String routing_key_fanout = "";

    public static void main(String[] args) {
        declare();
    }

    public static void declare() {
        try {
            RabbitMQUtil.createDeclareChannel(queue_direct, exchange_direct, BuiltinExchangeType.DIRECT, routing_key_direct, true).close();
            RabbitMQUtil.createDeclareChannel(queue_fanout_1, exchange_fanout, BuiltinExchangeType.FANOUT, routing_key_fanout, true).close();
            RabbitMQUtil.createDeclareChannel(queue_fanout_2, exchange_fanout, BuiltinExchangeType.FANOUT, routing_key_fanout, true).close();
            RabbitMQUtil.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

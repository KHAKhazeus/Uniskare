package com.uniskare.eureka_skill.service.impl.RMQ;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author : SCH001
 * @description :
 */
public class rmq {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv)throws java.io.IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("49.235.255.145");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-message-ttl", 10000);
        channel.queueDeclare(QUEUE_NAME, false, false, false, args);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}

package com.renvema.sharesmarketservice.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleMessage(String message) {
        System.out.println("Received message from RabbitMQ: " + message);
    }
}

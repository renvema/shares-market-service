package com.renvema.sharesmarketservice.rabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renvema.sharesmarketservice.controller.dto.ShareDto;
import com.renvema.sharesmarketservice.service.MarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final ObjectMapper objectMapper;

    private final MarketService marketService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleMessage(String message) {
        try {
            ShareDto share = objectMapper.readValue(message, ShareDto.class);

            marketService.processOperation(share);

            System.out.println("Message processed and saved successfully: " + share);
        } catch (Exception e) {
            log.error("Error processing message: " + e.getMessage());
        }
        System.out.println("Received message from RabbitMQ: " + message);
    }
}

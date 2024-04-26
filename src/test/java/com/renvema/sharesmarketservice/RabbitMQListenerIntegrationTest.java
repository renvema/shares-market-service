package com.renvema.sharesmarketservice;

import com.renvema.sharesmarketservice.rabbitMQ.RabbitMQListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class RabbitMQListenerIntegrationTest {

    @Autowired
    private RabbitMQListener rabbitMQListener;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testRabbitMQConnection() {
        assertNotNull(connectionFactory);
        assertNotNull(rabbitTemplate);
        
        try {
            Connection connection = connectionFactory.createConnection();
            assertNotNull(connection);
            assertTrue(connection.isOpen());
            connection.close();
        } catch (Exception e) {
            fail("Failed to establish connection to RabbitMQ: " + e.getMessage());
        }
    }

    @Test
    public void testMessageProcessing() throws InterruptedException {

        // Given
        String message = "{\"username\": \"renvema\", \"ticker\": \"APPL\", \"operation\": \"BUY\", \"amount\": 200}";
        // When
        rabbitTemplate.convertAndSend("market-events", message);

        // Wait for message processing
        Thread.sleep(1000);

        // Then
        // TODO: Add assertions here to verify the expected outcome of message processing
    }
}

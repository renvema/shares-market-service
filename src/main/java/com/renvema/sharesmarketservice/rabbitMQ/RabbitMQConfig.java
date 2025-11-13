package com.renvema.sharesmarketservice.rabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Value("${rabbitmq.queue}")
    private String queueName;

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.binding}")
    private String bindingKey;


    @Bean
    public Queue myQueue() {
        return QueueBuilder.durable(queueName)
                .build();
    }

    @Bean
    public DirectExchange myExchange() {
        return ExchangeBuilder.directExchange(exchangeName)
                .build();
    }

    @Bean
    public Binding myBinding() {
        return BindingBuilder.bind(myQueue()).to(myExchange()).with(bindingKey);
    }

}

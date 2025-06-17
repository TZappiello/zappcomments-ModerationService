package com.zappcomments.zappcomments.moderationservice.infrastructure.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    @Bean
    public Queue queuePosts() {
        return QueueBuilder.durable("text-processor-service.post-processing.v1.q").build();
    }

    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange("text-processor-service.post-processing.v1.e").build();
    }

    @Bean
    public Binding bindingPosts() {
        return BindingBuilder.bind(queuePosts()).to(fanoutExchange());
    }
}

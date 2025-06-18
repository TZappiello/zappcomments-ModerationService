package com.zappcomments.zappcomments.moderationservice.infrastructure.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String TEXT_PROCESSOR_SERVICE_POST_PROCESSING = "text-processor-service.post-processing.v1.q";

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public Queue queuePosts() {
        return QueueBuilder.durable(TEXT_PROCESSOR_SERVICE_POST_PROCESSING).build();
    }

    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange("text-processor-service.post-processing.v1.e").build();
    }

    @Bean
    public Binding bindingPosts() {
        return BindingBuilder.bind(queuePosts()).to(fanoutExchange());
    }
}

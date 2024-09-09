package com.example.CommonService;

import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue userStatsQueue() {
        return new Queue("text1", true);
    }

    @Bean
    public TopicExchange userStatsExchange() {
        return new TopicExchange("交换机");
    }

    @Bean
    public Binding userStatsBinding(Queue userStatsQueue, TopicExchange userStatsExchange) {
        return BindingBuilder.bind(userStatsQueue)
                .to(userStatsExchange)
                .with("rk");
    }
}
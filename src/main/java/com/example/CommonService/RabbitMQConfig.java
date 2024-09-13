package com.example.CommonService;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("118.31.77.139");
//        connectionFactory.setUsername("admin");
//        connectionFactory.setPassword("hxww");
//        connectionFactory.setPort(5672);
//        return connectionFactory;
//    }





    @Bean
    public Queue userStatsQueue1() {
        return new Queue("text1", true);
    }

    @Bean
    public Queue userStatsQueue2() {
        return new Queue("text2", true);
    }

    @Bean
    public TopicExchange userStatsExchange() {
        return new TopicExchange("交换机");
    }


    @Bean
    public Binding userStatsBinding1(Queue userStatsQueue1, TopicExchange userStatsExchange) {
        return BindingBuilder.bind(userStatsQueue1)
                .to(userStatsExchange)
                .with("rk");
    }

    @Bean
    public Binding userStatsBinding2(Queue userStatsQueue2, TopicExchange userStatsExchange) {
        return BindingBuilder.bind(userStatsQueue2)
                .to(userStatsExchange)
                .with("rk2");
    }

    //确保消息转换器能够处理你要发送的对象类型。如果你希望发送自定义对象（如 ClientRequestData）
    //你应该使用 Jackson2JsonMessageConverter 或类似的 JSON 转换器，而不是 SimpleMessageConverter
    //为了解决传输将传输的model转化为json的问题
//    @Bean
//    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate();
//        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
//        return rabbitTemplate;
//    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }
}

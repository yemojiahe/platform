package com.example.CommonService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName = "交换机";
    private final String routingKey = "rk";

    @Autowired
    public MessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // 发送消息到 RabbitMQ
    public void sendMessage(Object message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }


    //接收消息
    @RabbitListener(queues = "text1")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
        // 处理消息
    }
}


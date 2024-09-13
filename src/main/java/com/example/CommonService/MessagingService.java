package com.example.CommonService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class MessagingService {

    public final RabbitTemplate rabbitTemplate;
//    private final String exchangeName = "交换机";
//    private final String routingKey = "rk";

    @Autowired
    public MessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }





}


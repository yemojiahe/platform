package com.example.textMq;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveHandler {

    //监听NAME_HELLO队列
//    @RabbitListener(queues = "text1")
//    public void receiveHelloQueueMessage(String msg, Message message, AMQP.Channel channel) {
//        System.out.println("消费者收到消息:"+msg);
//    }
}


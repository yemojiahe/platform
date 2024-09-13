package com.example.textMq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.BlockingQueueConsumer;
import org.springframework.amqp.rabbit.support.ActiveObjectCounter;
import org.springframework.amqp.rabbit.support.Delivery;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;

public interface textwaitingdelete1 {
    void receiveMessage(String message);

    void handleDelivery(Delivery delivery);
}




//package com.example.textMq;
//
//
//import org.springframework.amqp.core.AcknowledgeMode;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.listener.BlockingQueueConsumer;
//import org.springframework.amqp.rabbit.support.ActiveObjectCounter;
//import org.springframework.amqp.rabbit.support.Delivery;
//import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;
////通过继承MessagingService实现监听
//
//
//public class textwaitingdelete extends BlockingQueueConsumer implements textwaitingdelete1 {
//
//
//    public textwaitingdelete(ConnectionFactory connectionFactory, MessagePropertiesConverter messagePropertiesConverter, ActiveObjectCounter<BlockingQueueConsumer> activeObjectCounter, AcknowledgeMode acknowledgeMode, boolean transactional, int prefetchCount, String... queues) {
//        super(connectionFactory, messagePropertiesConverter, activeObjectCounter, acknowledgeMode, transactional, prefetchCount, queues);
//    }
//
//    @Override
//    public void receiveMessage(String message) {
//        System.out.println("测试类正在监听队列: " + message);
//    }
//
//    @Override
//    public void handleDelivery(Delivery delivery) {
//        String message = new String(delivery.getBody());
//        receiveMessage(message);
//    }
//}

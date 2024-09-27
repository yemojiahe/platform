package com.example.textMq;

import com.example.CommonService.MessagingService;
import com.example.Queryprocessing.QueryModel.ClientRequestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class TextWaitingDelete2 extends MessagingService {

    @Autowired
    public TextWaitingDelete2(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    @RabbitListener(queues = "text1")
    public void receiveMessage(String message) {
        System.out.println("消费者检测: " + message);
        message=message+"gjc";
        System.out.println("TEXTlinster"+message);
        //将处理的消息放在原先队列
        rabbitTemplate.convertAndSend("rk", message);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "text2")
    public void receiveData(String msg) {
        try {
            System.out.println("消费者检测:");
            // 将 JSON 消息转换为 Data 对象
            ClientRequestData data = objectMapper.readValue(msg, ClientRequestData.class);
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


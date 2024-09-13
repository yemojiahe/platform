package com.example.Queryprocessing.service;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.CommonService.MessagingService;
import com.example.Queryprocessing.QueryModel.ClientRequestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class ForwardService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    // 使用rabbitsmq转发给特征用户统计模块
    // forwardservice.crowdCategoriesQuery()
    private final MessagingService messagingService;
    //text
    public String process_msg;
    public ClientRequestData requestData;

    @Autowired
    public ForwardService(MessagingService messagingService) {
        this.messagingService = messagingService;
    }


    //将特征人群转发给特征用户统计模块
    public void crowdCategoriesQueryToQueue(ClientRequestData requestData) {
        // 发送消息到 RabbitMQ
        Map<String, ClientRequestData.Item> items = requestData.getItems();
        // 遍历每个 Item
        for (ClientRequestData.Item item : items.values()) {
            // 获取当前 Item 的 CrowdCategories 字符串
            String crowdCategories = item.getCrowdCategories();
            System.out.println(crowdCategories);
            // 处理每个 CrowdCategories 对象
            messagingService.rabbitTemplate.convertAndSend("交换机", "rk", crowdCategories );
        }
    }

    //将得到ClientRequestData requestData转发给交易数据分析模块
    public void  ClientRequestDataToQueue(ClientRequestData requestData)  {

        ObjectMapper mapper = new ObjectMapper();
        String DataToQueue;
        try {
            DataToQueue = mapper.writeValueAsString(requestData);
        } catch (JsonProcessingException e) {
            System.out.println("错误报警");
            throw new RuntimeException(e);
        }
//        messagingService.sendMessage(DataToQueue , "rk2");
//        messagingService.rabbitTemplate.convertAndSend("交换机", "rk2", DataToQueue );
        // 发送 JSON 数据到 RabbitMQ
        rabbitTemplate.convertAndSend("交换机", "rk2", DataToQueue, message -> {
            message.getMessageProperties().setContentType("application/json");
            message.getMessageProperties().setReplyTo("text2");
            return message;
        });
    }

    @RabbitListener(queues = "text1")
    public void receiveString(String msg) {
        System.out.println(msg);
        process_msg = msg;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @RabbitListener(queues = "text2")
//    public ClientRequestData receiveData(String msg) {
//        System.out.println("Received message: " + msg);
//        try {
//            System.out.println("将得到ClientRequestData requestData转发给交易数据分析模块");
//            // 将 JSON 消息转换为 Data 对象
//            ClientRequestData data = objectMapper.readValue(msg, ClientRequestData.class);
//            requestData = data;
//            System.out.println("Converted Data: " + data);
//            return data;
//        } catch (Exception e) {
//            System.err.println("Error processing message: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return null;
//    }

    @RabbitListener(queues = "text2")
    public void receiveData(String msg) {
        System.out.println("Received message: " + msg);
        try {
            // 处理消息
            ClientRequestData data = objectMapper.readValue(msg, ClientRequestData.class);
            System.out.println("Converted Data: " + data);
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
        }
    }


}

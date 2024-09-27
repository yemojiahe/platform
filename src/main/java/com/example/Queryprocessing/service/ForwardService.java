package com.example.Queryprocessing.service;
import com.example.Queryprocessing.QueryModel.ServerResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.CommonService.MessagingService;
import com.example.Queryprocessing.QueryModel.ClientRequestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ForwardService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    // 使用rabbitsmq转发给特征用户统计模块
    // forwardservice.crowdCategoriesQuery()
    private final MessagingService messagingService;
    //text
    private String process_msg;
    public ClientRequestData requestData;

    @Autowired
    public ForwardService(MessagingService messagingService) {
        this.messagingService = messagingService;
    }


    /**
     * 将特征人群转发给特征用户统计模块
     * 将str类传值给text1队列，从text3接收数据
     */
    public ServerResponseData crowdCategoriesQueryToQueue(ClientRequestData requestData) {
        //初始化返回实例
        ServerResponseData responseData = new ServerResponseData();
        Map<String, ServerResponseData.Item> responseItems = new HashMap<>();

        //计算crowdCategories的人数
        Map<String, ClientRequestData.Item> items = requestData.getItems();
        for (ClientRequestData.Item item : items.values()) {
            String crowdCategories = item.getCrowdCategories();
            //消息发往消息队列
            messagingService.rabbitTemplate.convertAndSend("交换机", "rk", crowdCategories);


            //修改避免程序异步爆错
            String crowdCategoriesValue = null;
            long startTime = System.currentTimeMillis();
            long waitTime = 5000; // 5秒

            while (crowdCategoriesValue == null && (System.currentTimeMillis() - startTime) < waitTime) {
                crowdCategoriesValue = getProcess_msg(); //得到每种类别的人数数据
                if (crowdCategoriesValue != null) {
                    System.out.println("接收到消息: " + crowdCategoriesValue);
                } else {
                    try {
                        Thread.sleep(100); // 暂停100毫秒后再检查
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // 处理线程中断
                    }
                }
            }



            // 创建并填充新的 Item
            ServerResponseData.Item responseItem = new ServerResponseData.Item();
            List<String> response  = new ArrayList<>(Arrays.asList(crowdCategories , crowdCategoriesValue));;

            //不同crowdCategories的人数形成列表
            responseItem.setCrowdCategories(response);



            responseItems.put(crowdCategories, responseItem);
        }

        responseData.setItems(responseItems);
        return responseData;
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
        rabbitTemplate.convertAndSend("交换机", "rk2", DataToQueue, message -> {
            message.getMessageProperties().setContentType("application/json");
            message.getMessageProperties().setReplyTo("text2");
            return message;
        });
    }



    //接收来自特征用户统计模块的消息
    @RabbitListener(queues = "text1")
    public void receiveString(String msg) {
        System.out.println("第一次"+msg);
        setProcess_msg(msg);
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


    public String getProcess_msg() {
        return process_msg;
    }

    public void setProcess_msg(String process_msg) {
        this.process_msg = process_msg;
    }
}

package com.example.Queryprocessing.service;


import com.example.CommonService.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ForwardService {
    // 使用rabbitsmq转发给特征用户统计模块
    // forwardservice.crowdCategoriesQuery()
    private final MessagingService messagingService;

    @Autowired
    public ForwardService(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    public void crowdCategoriesQuery() {
        // 发送消息到 RabbitMQ
        messagingService.sendMessage("This is a test message");
    }
}

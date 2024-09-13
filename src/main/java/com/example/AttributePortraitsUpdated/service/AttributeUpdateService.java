package com.example.AttributePortraitsUpdated.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AttributeUpdateService {

    @Autowired
    private RestTemplate restTemplate;


    public void forwardStatistics(String encryptedVector) {
        // 将数据转发给特征用户统计模块
        restTemplate.postForObject("http://localhost:8081/featureUserStatistics/process", encryptedVector, String.class);

    }

    public void processCollectedData( ) {
        //收集数据处理
    }
}

package com.example.Queryprocessing.controller;


import com.example.CommonService.MessagingService;
import com.example.Queryprocessing.QueryModel.ClientRequestData;
import com.example.Queryprocessing.QueryModel.ServerResponseData;
import com.example.Queryprocessing.service.ForwardService;
import com.example.dictionary.model.Attribute;
import com.example.dictionary.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class QueryController {

    @Autowired
    ForwardService forwardservice;


    //问题，怎么构造响应的的数据类----直接在request中的item中加
    //二次处理单独定一个
    @PostMapping("/processData")
    @ResponseBody
    public  ResponseEntity<ServerResponseData> processClientRequest(@RequestBody ClientRequestData requestData) {
        //初始化返回消息类
        ServerResponseData responseData = new ServerResponseData();
        //转发给特征用户统计模块得到特征用户
        responseData = forwardservice.crowdCategoriesQueryToQueue(requestData);
        //监听到队列结果
        //转发给交易数据分析模块
//        forwardservice.ClientRequestDataToQueue(requestData);
        String data = forwardservice.getProcess_msg();
        System.out.println("controller接到消息了"+data);
        return ResponseEntity.ok(responseData);
    }
//    public ServerResponseData crowdCategoriesQueryToQueue(ClientRequestData requestData) {
//        //初始化返回实例
//        ServerResponseData responseData = new ServerResponseData();
//        Map<String, ServerResponseData.Item> responseItems = new HashMap<>();
//
//        //计算crowdCategories的人数
//        Map<String, ClientRequestData.Item> items = requestData.getItems();
//        for (ClientRequestData.Item item : items.values()) {
//            String crowdCategories = item.getCrowdCategories();
//            //消息发往消息队列
//            messagingService.rabbitTemplate.convertAndSend("交换机", "rk", crowdCategories);
//            //得到每种类别的人数数据
//            String crowdCategoriesValue = forwardservice.getProcess_msg();
//            // 创建并填充新的 Item
//            ServerResponseData.Item responseItem = new ServerResponseData.Item();
//            List<String> response  = new ArrayList<>(Arrays.asList(crowdCategories , crowdCategoriesValue));;
//
//            //不同crowdCategories的人数形成列表
//            responseItem.setCrowdCategories(response);
//
//
//
//            responseItems.put(crowdCategories, responseItem);
//        }
//
//        responseData.setItems(responseItems);
//        return responseData;
//    }





    //向前端更新属性字典
    @Autowired
    private AttributeService attributeService;

    @PostMapping("/changeAttribute")
    public ResponseEntity<String>  changeAttribute(@RequestBody  List<Attribute>  newAttribute) {
        attributeService.updateAttributes(newAttribute);
        return ResponseEntity.ok("属性修改成功");
    }
}

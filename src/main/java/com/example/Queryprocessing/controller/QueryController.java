package com.example.Queryprocessing.controller;


import com.example.Queryprocessing.QueryModel.ClientRequestData;
import com.example.Queryprocessing.service.ForwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class QueryController {

    @Autowired
    ForwardService forwardservice;

    @PostMapping("/processData")
    @ResponseBody
    public String processClientRequest(@RequestBody ClientRequestData requestData) {
        // 从 ClientRequestData 中获取 item 列表

        Map<String, ClientRequestData.Item> items = requestData.getItems();
        System.out.println(requestData.getSecondaryProcessingMethod());
        // 遍历每个 Item
        for (ClientRequestData.Item item : items.values()) {
            // 获取当前 Item 的 CrowdCategories 字符串
            String crowdCategories = item.getCrowdCategories();
            System.out.println(crowdCategories);
            // 处理每个 CrowdCategories 对象
            if (crowdCategories != null && !crowdCategories.isEmpty()) {
                // 转发给特征用户统计模块
                forwardservice.crowdCategoriesQuery();

                // 在这里可以对 crowdCategories 进行更多操作
            }
        }

        return "Processing complete";
    }
}

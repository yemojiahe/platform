package com.example.Queryprocessing.controller;


import com.example.Queryprocessing.QueryModel.ClientRequestData;
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

import java.util.List;
import java.util.Map;

@Controller
public class QueryController {

    @Autowired
    ForwardService forwardservice;

    @PostMapping("/processData")
    @ResponseBody

    public String processClientRequest(@RequestBody ClientRequestData requestData) {
        //转发给特征用户统计模块
        forwardservice.crowdCategoriesQueryToQueue(requestData);

        //监听到队列结果
        //转发给交易数据分析模块
        forwardservice.ClientRequestDataToQueue(requestData);
        System.out.println(forwardservice.process_msg);
        System.out.println(forwardservice.requestData);
        return "Processing complete";
    }


    //向前端更新属性字典
    @Autowired
    private AttributeService attributeService;

    @PostMapping("/changeAttribute")
    public ResponseEntity<String>  changeAttribute(@RequestBody  List<Attribute>  newAttribute) {
        attributeService.updateAttributes(newAttribute);
        return ResponseEntity.ok("属性修改成功");
    }
}

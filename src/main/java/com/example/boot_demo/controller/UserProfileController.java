
//属性画像更新模块的控制器

package com.example.boot_demo.controller;


import com.example.boot_demo.model.CollectedData;
import com.example.boot_demo.model.DataCollectionRequest;

import com.example.boot_demo.service.AttributeUpdateService;
import com.example.boot_demo.service.DictionaryUpdateService;


import com.example.dictionary.model.Attribute;
import com.example.dictionary.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/text")
public class UserProfileController {

    @Autowired
    private AttributeUpdateService attributeUpdateService;

    @Autowired
    private DictionaryUpdateService dictionaryUpdateService;


    //收集数据转发给特征统计模块
    @PostMapping("/notify-data-collection")
    public ResponseEntity<String> notifyDataCollection(@RequestBody DataCollectionRequest request) {
        //DataCollectionRequest 对象的内容来自于每个请求的请求体。不同 IP 发出的请求会分别创建各自的 DataCollectionRequest
        //实例，因此请求之间的数据不会相互覆盖。每次请求都是独立处理的，系统会处理请求体中的数据，而不会将来自不同 IP 的数据混合或覆盖

        String encryptedVector = request.getEncryptedVector();
        attributeUpdateService.forwardStatistics(encryptedVector);
        return ResponseEntity.ok("Data collection request has been sent.");
    }

//    // 数据收集后转发
//    @PostMapping("/upload-data")
//    public ResponseEntity<String> uploadData(@RequestBody CollectedData collectedData) {
//        // 处理上传的数据（数据先接收，然后要转发）
//        attributeUpdateService.processCollectedData();
//        // 从 collectedData 中提取加密向量并转发
//        String encryptedVector = collectedData.getEncryptedVector();
//        attributeUpdateService.forwardStatistics(encryptedVector);
//
//        return ResponseEntity.ok("Data received and processed.");
//    }


    //向前端更新属性字典
    @Autowired
    private AttributeService attributeService;

    @GetMapping("/dictionary")
    public ResponseEntity<List<Attribute>> getAttributeDictionary() {
        List<Attribute> attributes = attributeService.getAllAttributes();
        System.out.println(attributes);
        return ResponseEntity.ok(attributes);
    }




}
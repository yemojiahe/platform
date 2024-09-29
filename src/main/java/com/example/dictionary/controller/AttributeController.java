package com.example.dictionary.controller;
/* 该接口开放给企业更改属性字典业务（从查询请求响应模块切换到本模块） */
import com.example.dictionary.model.Attribute;
import com.example.dictionary.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attributes")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @PostMapping
    public ResponseEntity<String> createAttribute(@RequestBody Attribute attribute) {
        attributeService.submitForReview(attribute, "增加");
        return ResponseEntity.ok("属性新增请求已提交，等待审核");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAttribute(@PathVariable Long id, @RequestBody Attribute attribute) {
        attributeService.submitForReview(attribute, "修改");
        return ResponseEntity.ok("属性修改请求已提交，等待审核");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttribute(@PathVariable Long id) {
        attributeService.submitForReview(id, "删除");
        return ResponseEntity.ok("属性删除请求已提交，等待审核");
    }

    @GetMapping
    public List<Attribute> getAllAttributes() {
        return attributeService.getAllAttributes();
    }
}

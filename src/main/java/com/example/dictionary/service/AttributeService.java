package com.example.dictionary.service;

//该接口是提供给企业的属性字典查询请求响应的服务类

import com.example.dictionary.Repository.AttributeRepository;
import com.example.dictionary.Repository.AuditLogRepository;
import com.example.dictionary.model.Attribute;
import com.example.dictionary.model.AuditLog;
import com.example.dictionary.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttributeService {


    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    public void submitForReview(Attribute attribute, String action) {
        //action = 增加，修改，删除
        System.out.println("程序定位2");
        attribute.setStatus(Status.PENDING); //属性表先存入待审核
        attribute.setCreatedAt(LocalDateTime.now()); //存放修改时间
        System.out.println("程序定位3" + attribute.getStatus() + attribute.getCreatedAt());
        attributeRepository.save(attribute);
        System.out.println("属性表成功存入");
        AuditLog log = new AuditLog(); //审核表
        log.setAttribute(attribute);
        System.out.println("程序定位4"+log.getAttribute().getId());
        log.setAction(action); // 存入操作行为
        System.out.println("程序定位5"+log.getAction());
        log.setRequestedBy(getCurrentUserId()); //存入修改者id
        log.setStatus(Status.PENDING);
        System.out.println("程序定位6"+log.getStatus());
        log.setCreatedAt(LocalDateTime.now());
        //bzhida
        auditLogRepository.save(log);

    }

    public void submitForReview(Long id, String action) {

    }

    public List<Attribute> getAllAttributes() {
        return attributeRepository.findAll();
    }

    // 获取当前用户ID的方法
    private Long getCurrentUserId() {
        // 实现获取当前用户ID的方法
        return 1L; // 示例：返回一个固定的用户ID
    }
}


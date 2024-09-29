package com.example.dictionary.service;

import com.example.dictionary.Repository.AttributeRepository;
import com.example.dictionary.Repository.AuditLogRepository;
import com.example.dictionary.model.Attribute;
import com.example.dictionary.model.AuditLog;
import com.example.dictionary.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService {

    @Autowired
    private AuditLogRepository auditLogRepository;
    @Autowired
    private AttributeRepository attributeRepository;

    public List<AuditLog> getPendingAudits() {
        return auditLogRepository.findByStatus(Status.PENDING);  //找到所有未审核的数据的全部信息
    }

    public void reviewAudit(Long id, boolean approved, String... reason) {
        AuditLog log = auditLogRepository.findById(id).orElseThrow(() -> new RuntimeException("审核记录未找到"));

        if (approved) {
            log.setStatus(Status.APPROVED);
            // 更新属性表
            Attribute attribute = log.getAttribute();
            attribute.setStatus(Status.APPROVED);
            attributeRepository.save(attribute);
        } else {
            log.setStatus(Status.REJECTED);
            log.setAdminReviewed(getCurrentAdminId());
            // 可以记录拒绝理由
        }

        auditLogRepository.save(log);
    }

    private Long getCurrentAdminId() {
        // 实现获取当前管理员ID的方法
        return 1L; // 示例：返回一个固定的管理员ID
    }
}

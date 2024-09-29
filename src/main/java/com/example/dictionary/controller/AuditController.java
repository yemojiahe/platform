package com.example.dictionary.controller;
/*该接口为管理员操作接口用来最终确定属性字典*/
import com.example.dictionary.model.AuditLog;
import com.example.dictionary.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audits")
public class AuditController {

    @Autowired
    private AuditService auditService;

    //找到所有待审核的数据
    @GetMapping("/pending")
    public List<AuditLog> getPendingAudits() {
        return auditService.getPendingAudits();
    }


    //同意某一条请求
    @PostMapping("/{id}/approve")
    public ResponseEntity<String> approveAudit(@PathVariable Long id) {
        auditService.reviewAudit(id, true);
        return ResponseEntity.ok("审核通过");
    }

    //拒绝某一条请求
    @PostMapping("/{id}/reject")
    public ResponseEntity<String> rejectAudit(@PathVariable Long id, @RequestBody String reason) {
        auditService.reviewAudit(id, false, reason);
        return ResponseEntity.ok("审核拒绝");
    }
}

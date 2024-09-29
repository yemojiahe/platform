package com.example.dictionary.model;

/*
        审核表：
        id (主键)
        attribute_id (外键，指向属性表)
        action (操作: 增加、修改、删除)
        requested_by (申请人ID)
        admin_reviewed (审核人ID)
        status (审核结果: 待审核、通过、拒绝)
        created_at (创建时间)
        updated_at (更新时间)

*/

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    private String action; // 增加、修改、删除
    private Long requestedBy; // 申请人ID
    private Long adminReviewed; // 审核人ID
    private Status status; // 审核结果
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(Long requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Long getAdminReviewed() {
        return adminReviewed;
    }

    public void setAdminReviewed(Long adminReviewed) {
        this.adminReviewed = adminReviewed;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}


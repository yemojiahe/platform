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
import jakarta.persistence.Id;
import java.time.LocalDateTime;
@Table(name="audit_log")
@Entity
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attribute_id",referencedColumnName = "id")
    private Attribute attribute;

    @Column(name = "action")
    private String action; // 增加、修改、删除

    @Column(name = "requested_by")
    private Long requestedBy; // 申请人ID

    @Column(name = "admin_reviewed")
    private Long adminReviewed; // 审核人ID

    @Column(name = "status")
    private Status status; // 审核结果

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
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


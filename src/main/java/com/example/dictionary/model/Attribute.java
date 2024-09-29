package com.example.dictionary.model;
/*
属性表 (Attributes)：
id (主键)
name (属性名称)
value (属性值)
status (审核状态: 待审核、审核通过、审核拒绝)
created_at (创建时间)
updated_at (更新时间)
审核表 (AuditLogs)：
*/


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String value;

    @Enumerated(EnumType.STRING)
    private Status status; // 审核状态

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Version
    private Integer version; // 乐观锁版本号

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}


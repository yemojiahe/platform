package com.example.dictionary.Repository;
//增加属性审核表数据操纵层

import com.example.dictionary.model.AuditLog;
import com.example.dictionary.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByStatus(Status status);
}

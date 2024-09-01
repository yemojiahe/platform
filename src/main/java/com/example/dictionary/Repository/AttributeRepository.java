package com.example.dictionary.Repository;

import com.example.dictionary.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    // 这里可以添加自定义查询方法
}

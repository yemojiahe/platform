package com.example.dictionary.Repository;
//增加属性表的操控层
import com.example.dictionary.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

}

package com.example.dictionary.service;



import com.example.dictionary.Repository.AttributeRepository;
import com.example.dictionary.model.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    //实现修改
    public void updateAttributes(List<Attribute> attributes) {
        attributeRepository.saveAll(attributes);
    }

    public List<Attribute> getAllAttributes() {
        return attributeRepository.findAll();
    }

//    public List<String> getAttributeNames() {
//        return attributeRepository.findAll().stream()
//                .map(Attribute::getName)
//                .toList();
//    }
}

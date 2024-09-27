package com.example.datacollectiondemo.DataCollectionModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TagIDRepository extends JpaRepository<TagID, String> {
    TagID findByTagExist(String tagID);
}

package com.example.datacollectiondemo.DataCollectionModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ShuffledUserIDRepository extends JpaRepository<ShuffledUsersID, Integer> {
    ShuffledUsersID findByIdExist(Integer bigInteger);
}

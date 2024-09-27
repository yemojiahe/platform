package com.example.datacollectiondemo.DataCollectionModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t WHERE t.productId = :productId AND t.user = :user")
    Transaction findByProductIdAndUser(@Param("productId") Integer productId, @Param("user") ShuffledUsersID user);
}

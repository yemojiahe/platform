package com.example.datacollectiondemo.DataCollectionModel;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface ShuffledUsersTagsRepository extends JpaRepository<ShuffledUsersTags, Integer> {
    ShuffledUsersTags findByUserIdAndTagId(ShuffledUsersID userID, TagID tagIDEntity);
}


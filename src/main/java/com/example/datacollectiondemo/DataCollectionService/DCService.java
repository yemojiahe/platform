package com.example.datacollectiondemo.DataCollectionService;

import com.example.datacollectiondemo.DataCollectionModel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import jakarta.transaction.Transactional;

@org.springframework.stereotype.Service
public class DCService {
    @Autowired
    private ShuffledUserIDRepository shuffledUserIDRepository;
    @Autowired
    private TagIDRepository tagIDRepository;
    @Autowired
    private ShuffledUsersTagsRepository shuffledUsersTagsRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    /*交易数据保存*/
    @Transactional
    public void saveUserData(Data_ShangHu data_shangHu) {


        System.out.println("Received data: " + data_shangHu);
        /*若已有此用户，则不用新加此用户*/
        ShuffledUsersID userID =shuffledUserIDRepository.findByIdExist(data_shangHu.getUserSH_ID());
        System.out.println("查询结果: " + userID);



        if (userID == null) {
                userID = new ShuffledUsersID();
                userID.setIdExist(data_shangHu.getUserSH_ID());
                shuffledUserIDRepository.save(userID);
        }




        for (String tagID : data_shangHu.getLabel()) {

            /*查询是否数据库中已保存此标签，若无，则保存*/
            TagID tagIDEntity = tagIDRepository.findByTagExist(tagID);
            if (tagIDEntity == null) {
                tagIDEntity = new TagID();
                tagIDEntity.setTagExist(tagID);
                tagIDRepository.save(tagIDEntity);

            }

            /*查询数据库中是否已保存此用户与此标签的关联关系，若无，则保存（有个问题，若更新此用户标签，此用户旧标签关系是否删除）*/
            ShuffledUsersTags existingTagEntity = shuffledUsersTagsRepository.findByUserIdAndTagId(userID, tagIDEntity);
            if (existingTagEntity == null) {
                ShuffledUsersTags tagEntity = new ShuffledUsersTags();
                tagEntity.setUseId(userID);
                tagEntity.setTagId(tagIDEntity);
                shuffledUsersTagsRepository.save(tagEntity);
            }



        }

        /*查询数据库是否已保存此用户的某产品交易数据 用产品id与用户id查找*/
        for(Transaction transaction : data_shangHu.getTransactionsData()){
            Transaction existingTransaction = transactionRepository.findByProductIdAndUser(transaction.getProductId(), transaction.getUser());

            if (existingTransaction != null) {
                // 如果存在，则更新记录，即更新单价与售量
                existingTransaction.setUnitPrice(transaction.getUnitPrice());
                existingTransaction.setSalesAmount(transaction.getSalesAmount());
                transactionRepository.save(existingTransaction);
            } else {
                // 如果不存在，则保存新记录
                transactionRepository.save(transaction);
            }
        }


    }



    /*保存用户id的测试*/
    public ResponseEntity saveUserID(ShuffledUsersID shuffledUsersID) {
        System.out.println("Received data: " + shuffledUsersID);

        if(shuffledUsersID == null){
            return ResponseEntity.badRequest().body("UserID object cannot be null");
        }

        ShuffledUsersID shuffledUsersID1 = new ShuffledUsersID();
        shuffledUsersID1.setIdExist(shuffledUsersID.getIdExist());

        shuffledUserIDRepository.save(shuffledUsersID1);
        return null;

    }
}



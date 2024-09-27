package com.example.datacollectiondemo.DataCollectionService;

import com.example.datacollectiondemo.DataCollectionModel.ShuffledUserIDRepository;
import com.example.datacollectiondemo.DataCollectionModel.ShuffledUsersID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@Service
@RestController
public class TestService {
    @Autowired
    private ShuffledUserIDRepository repository;

    @GetMapping("/test")
    public ResponseEntity<List<String>> IDsaved() {
        try {
            ShuffledUsersID userId = new ShuffledUsersID();
            userId.setIdExist(1); // 手动设置 ID
            repository.save(userId);
            return ResponseEntity.ok(Arrays.asList("保存成功!"));
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Arrays.asList("保存失败: " + e.getMessage()));
        }

    }

}

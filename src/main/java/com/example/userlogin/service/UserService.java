package com.example.userlogin.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    // 模拟用户存储
    private final Map<String, String> userDatabase = new HashMap<>();

    // BCryptPasswordEncoder 用于密码的加密和匹配
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService() {
        // 假设的用户和加密的密码
        userDatabase.put("1", passwordEncoder.encode("1"));
        userDatabase.put("user2", passwordEncoder.encode("mypassword"));
    }

    public boolean authenticate(String username, String password) {
        // 从数据库获取存储的密码
        String storedPassword = userDatabase.get(username);
        if (storedPassword != null) {
            // 验证密码
            return passwordEncoder.matches(password, storedPassword);
        }
        return false;
    }
}
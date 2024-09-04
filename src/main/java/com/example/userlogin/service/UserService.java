package com.example.userlogin.service;

import com.example.userlogin.Repository.UserRepository;
import com.example.userlogin.model.User;
import com.example.userlogin.util.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //创建用户类
    @Transactional
    public User createUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("用户名已存在");
        }

        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(password, salt);


        User user = new User(username, hashedPassword, salt);


        return userRepository.save(user);
    }

    //找用户名
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    //校验登入
    public boolean verifyUserPassword(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();
        //计算输入密码的哈希值，结合用户的盐值
        String hashedPassword = PasswordUtils.hashPassword(password, user.getSalt());
        //将计算出的哈希密码与存储在用户记录中的哈希密码进行比较
        return hashedPassword.equals(user.getHashedPassword());
    }
}

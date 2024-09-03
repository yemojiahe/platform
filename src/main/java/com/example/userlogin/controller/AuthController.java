//该模块任务
//利用jpa防止sql注入
//加盐哈希防止彩虹表攻击
//jwt鉴权身份认证
//9.4实现

package com.example.userlogin.controller;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.userlogin.service.JwtUtil;
import com.example.userlogin.model.LoginRequest;
import com.example.userlogin.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AuthController {


    @Autowired
    private UserService userService;


    @PostMapping("/login")
    //接收用户名和密码
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {


        // 验证用户名和密码
        if (userService.authenticate(username, password)) {
            // 生成 JWT token
            String token = JwtUtil.generateToken(username);
            // 返回 token
            return ResponseEntity.ok(token);
        } else {
            // 返回未授权状态
            System.out.println("1");
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}



//该模块任务
//利用jpa防止sql注入
//加盐哈希防止彩虹表攻击
//jwt鉴权身份认证
//后期需要换成json的数据格式返回
//9.4实现

package com.example.userlogin.controller;
import com.example.userlogin.util.JwtUtil;
import com.example.userlogin.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.userlogin.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class AuthController {


    private UserService userService;

    @Autowired
    public void UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestParam String username, @RequestParam String password) {
        //首先验证密码强度
        if (!PasswordUtils.validatePassword(password)) {
            return ResponseEntity.status(400).body("密码强度不够，密码长度大于8，需包含字符，数字，和标点符号");
        }
        try {
            userService.createUser(username, password);
            return ResponseEntity.ok("用户创建成功");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        //首先验证密码强度
        if (!PasswordUtils.validatePassword(password)) {
            return ResponseEntity.status(400).body("密码强度不够，密码长度大于8，需包含字符，数字，和标点符号");
        }
        boolean isValid = userService.verifyUserPassword(username, password);
        if (isValid) {
            String token = JwtUtil.generateToken(username);

            return ResponseEntity.ok("登录成功"+token);
        } else {
            return ResponseEntity.status(401).body("用户名或密码错误");
        }
    }

}



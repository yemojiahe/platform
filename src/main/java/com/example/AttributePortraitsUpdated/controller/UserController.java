
//
package com.example.boot_demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/api-text")
public class UserController {

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> response = new HashMap<>();

        boolean success = authenticate(username, password);
        if (success) {
            System.out.println("1");
            response.put("success", true);
            response.put("message", "登录成功");
        } else {
            response.put("success", false);
            response.put("message", "用户名或密码错误");
        }

        return response;
    }

    private boolean authenticate(String username, String password) {
        return "1".equals(username) && "1".equals(password);
    }


}
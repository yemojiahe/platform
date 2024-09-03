package com.example.userlogin.model;

public class AuthResponse {
    private String token;

    // 无参构造函数
    public AuthResponse() {}

    // 带参构造函数
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter 和 Setter 方法
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}


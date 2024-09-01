package com.example.boot_demo.model;

public class CollectedData {
    private String EdgeServerId; // 边缘服务器id
    private String encryptedVector; // 边缘服务器聚合的加密向量
    // Getters and Setters
    public String getEncryptedVector() {
        return encryptedVector;
    }
}
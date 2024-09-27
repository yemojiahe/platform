package com.example.AttributePortraitsUpdated.model;
import com.fasterxml.jackson.annotation.JsonProperty;


//
public class DataCollectionRequest {
//    private String EdgeServerId; // 边缘服务器id
//    private String encryptedVector; // 边缘服务器聚合的加密向量

    //即使它们看起来匹配，实际的映射可能因不同的 JSON 序列化库或配置而失败
    @JsonProperty("EdgeServerId")
    private String EdgeServerId;

    @JsonProperty("encryptedVector")
    private String encryptedVector;

    // Getters and Setters
    public String getEdgeServerId() {
        return EdgeServerId;
    }

    public void setEdgeServerId(String EdgeServerId) {
        this.EdgeServerId = EdgeServerId;
    }

    public String getEncryptedVector() {
        return encryptedVector;
    }

    public void setEncryptedVector(String encryptedVector) {
        this.encryptedVector = encryptedVector;
    }

}
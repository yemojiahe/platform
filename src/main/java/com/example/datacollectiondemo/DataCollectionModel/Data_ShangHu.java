package com.example.datacollectiondemo.DataCollectionModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.List;


//用户数据以此形式传进
public class Data_ShangHu {

    //用户的ID（系统排序所得）
    @JsonProperty("UserSH_ID")
    private Integer UserSH_ID;

    @JsonProperty("TransactionsData")
    //用户的交易数据
    private List<Transaction> TransactionsData;
    //用户的标签

    @JsonProperty("Label")
    //用户的标签
    private List<String> Label;

    /*Getter and Setter*/
    public Integer getUserSH_ID() {
        return UserSH_ID;
    }

    public void setUserSH_ID(Integer userSH_ID) {
        UserSH_ID = userSH_ID;
    }

    public List<Transaction> getTransactionsData() {
        return TransactionsData;
    }

    public void setTransactionsData(List<Transaction> transactionsData) {
        TransactionsData = transactionsData;
    }

    public List<String> getLabel() {
        return Label;
    }

    public void setLabel(List<String> label) {
        Label = label;
    }

    /*字符串型输出*/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"UserSH_ID\": ").append(UserSH_ID).append(",\n");
        sb.append("  \"TransactionsData\": [\n");

        for (Transaction transaction : TransactionsData) {
            sb.append("    ").append(transaction.toString()).append(",\n");
        }

        // Remove the last comma if there are transactions
        if (!TransactionsData.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove last comma and newline
        }

        sb.append("\n  ],\n");
        sb.append("  \"Label\": ").append(Label).append("\n");
        sb.append("}");

        return sb.toString();
    }

    // Getters and setters for UserSH_ID, TransactionsData, and Label
}


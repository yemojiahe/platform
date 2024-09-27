package com.example.Queryprocessing.QueryModel;

import java.util.List;

public class UserTransaction {
    private long userSHId;
    private List<Transaction> transactionsData;
    private List<String> label;

    // 构造函数
    public UserTransaction(long userSHId, List<Transaction> transactionsData, List<String> label) {
        this.userSHId = userSHId;
        this.transactionsData = transactionsData;
        this.label = label;
    }

    // Getter 和 Setter
    public long getUserSHId() {
        return userSHId;
    }

    public void setUserSHId(long userSHId) {
        this.userSHId = userSHId;
    }

    public List<Transaction> getTransactionsData() {
        return transactionsData;
    }

    public void setTransactionsData(List<Transaction> transactionsData) {
        this.transactionsData = transactionsData;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public static class Transaction {
        private int transactionId;
        private int productId;
        private User user;
        private double unitPrice;
        private int salesAmount;

        // 构造函数
        public Transaction(int transactionId, int productId, User user, double unitPrice, int salesAmount) {
            this.transactionId = transactionId;
            this.productId = productId;
            this.user = user;
            this.unitPrice = unitPrice;
            this.salesAmount = salesAmount;
        }

        // Getter 和 Setter
        public int getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(int transactionId) {
            this.transactionId = transactionId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getSalesAmount() {
            return salesAmount;
        }

        public void setSalesAmount(int salesAmount) {
            this.salesAmount = salesAmount;
        }
    }

    public static class User {
        private long idExist;

        // 构造函数
        public User(long idExist) {
            this.idExist = idExist;
        }

        // Getter 和 Setter
        public long getIdExist() {
            return idExist;
        }

        public void setIdExist(long idExist) {
            this.idExist = idExist;
        }
    }
}

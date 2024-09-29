package com.example.datacollectiondemo.DataCollectionModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/*交易数据*/
@jakarta.persistence.Entity
@Table(name="transactions")
public class Transaction {

    /*交易数据的条数，自增*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore // 确保在 JSON 中不包括该字段
    private Integer transaction_id;

    /*产品的 id ,后续也可以根据需要改为产品名称*/
    @Column(name = "product_id")
    private Integer productId;

    /*此条交易数据所属的用户 id*/
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "ID_exist")
    private ShuffledUsersID user;

    /*产品单价*/
    @Column(name = "unit_price")
    private Integer unitPrice;

    /*产品销量*/
    @Column(name = "sales_amount")
    private Integer salesAmount;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("      \"transaction_id\": ").append(transaction_id).append(",\n");
        sb.append("      \"product_id\": ").append(productId).append(",\n");
        sb.append("      \"user\": ").append(user.toString()).append(",\n");
        sb.append("      \"unit_price\": ").append(unitPrice).append(",\n");
        sb.append("      \"sales_amount\": ").append(salesAmount).append("\n");
        sb.append("    }");
        return sb.toString();
    }

    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public ShuffledUsersID getUser() {
        return user;
    }

    public void setUser(ShuffledUsersID user) {
        this.user = user;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Integer salesAmount) {
        this.salesAmount = salesAmount;
    }
}

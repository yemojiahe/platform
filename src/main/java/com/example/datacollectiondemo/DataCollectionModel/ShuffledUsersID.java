package com.example.datacollectiondemo.DataCollectionModel;

import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigInteger;

/*表中只有一列，存已出现的用户id（此id指混淆后系统自动分配的id，一般从1往后分配）*/
@jakarta.persistence.Entity
@Table(name = "shuffled_users_id")
public class ShuffledUsersID {


    @Id
    @Column(name = "ID_exist")
//    @JsonProperty("idExist")
    private Integer idExist;

    @Override
    public String toString() {
        return "{ \"ID_exist\": " + idExist + " }";
    }


    public Integer getIdExist() {
        return idExist;
    }

    public void setIdExist(Integer idExist) {
        this.idExist = idExist;
    }
}

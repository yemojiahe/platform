package com.example.datacollectiondemo.DataCollectionModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigInteger;

/*存已出现的标签 tag_id*/
@Entity
@Table(name = "tag_id")
public class TagID{

    @Id
    @Column(name = "tag_exist")
    private String tagExist;

    public String getTagExist() {
        return tagExist;
    }

    public void setTagExist(String  tagExist) {
        this.tagExist = tagExist;
    }
}

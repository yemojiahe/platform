package com.example.datacollectiondemo.DataCollectionModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

package com.example.datacollectiondemo.DataCollectionModel;

import javax.persistence.*;
import java.math.BigInteger;

/*
用户的 id关联标签
eg:id为5的用户标签有 1 3 4
则存进三组数据：  5 1；
                5 3；
                5 4；
*/
@Entity
@Table(name = "shuffled_users_tags")
public class ShuffledUsersTags {

    /*记录表中关联的条数，自增类型*/
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*用户的 id，与存进的 UserSH_ID 一致*/
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "ID_exist")
    private ShuffledUsersID userId;

    /*用户的标签，此标签也要存进表 tag_id，以此为外键*/
    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "tag_exist")
    private TagID tagId;

    /*Getter and Setter*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public ShuffledUsersID getUseId() {
        return userId;
    }

    public void setUseId(ShuffledUsersID useId) {
        this.userId = useId;
    }

    public TagID getTagId() {
        return tagId;
    }

    public void setTagId(TagID tagId) {
        this.tagId = tagId;
    }
}
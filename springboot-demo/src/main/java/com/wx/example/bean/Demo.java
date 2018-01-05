package com.wx.example.bean;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

/**
 * Created by wangxiong on 2017/12/20.
 */

@Entity
@Table(name="user")
public class Demo {

    @JSONField(serialize=false)
    @Id @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

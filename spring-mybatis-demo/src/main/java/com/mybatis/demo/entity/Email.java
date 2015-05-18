package com.mybatis.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by louis on 2015/5/18.
 */
public class Email implements Serializable{
    private long id;
    private String content;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

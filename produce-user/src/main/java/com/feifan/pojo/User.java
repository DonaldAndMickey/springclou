package com.feifan.pojo;

import java.util.Date;

/**
 * @author Donald
 * @create 2019-01-30 23:06
 */
public class User {
    private int id;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public User() {
    }
}

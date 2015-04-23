package com.example.apuser.mymicroblogging.domain.model;

import java.util.Date;

/**
 * Created by apuser on 4/23/15.
 */
public class Status {
    private long id;
    private Date created_at;
    private String text;
    private String user;

    public Status(long id, Date created_at, String text, String user) {
        this.id = id;
        this.created_at = created_at;
        this.text = text;
        this.user = user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getId() {

        return id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }
}

package com.example.apuser.mymicroblogging.domain.repository.api.model;

import java.util.Date;

/**
 * Created by apuser on 4/23/15.
 */
public class ResponseStatus {
    private long id;
    private String created_at;
    private String text;
    private User user;

    ResponseStatus(long id, String createdAt, User user, String message) {
        this.id = id;
        this.created_at = createdAt;
        this.user = user;
        this.text = message;
    }

    public long getId() {
        return id;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return text;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreatedAt(String createdAt) {
        this.created_at = createdAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMessage(String message) {
        this.text = message;
    }
}

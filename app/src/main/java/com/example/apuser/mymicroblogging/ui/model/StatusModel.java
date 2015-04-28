package com.example.apuser.mymicroblogging.ui.model;

/**
 * Created by mikelee on 4/27/15.
 */
public class StatusModel {
    public StatusModel(String user, String message, long createdAt) {
        this.user = user;
        this.message = message;
        this.createdAt = createdAt;
    }

    private String user;
    private String message;
    private long createdAt;

    public void setUser(String user) {
        this.user = user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}

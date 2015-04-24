package com.example.apuser.mymicroblogging.domain.repository.api.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.Date;

/**
 * Created by apuser on 4/23/15.
 */
@Root(strict=false, name="status")
public class ResponseStatus {
    @Element(name="id")
    private long id;
    @Element(name="created_at")
    private String created_at;
    @Element(name="text")
    private String text;
    @Element(name="user")
    private User user;

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

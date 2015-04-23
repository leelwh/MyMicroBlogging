package com.example.apuser.mymicroblogging.domain.repository.api.model;

/**
 * Created by apuser on 4/23/15.
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

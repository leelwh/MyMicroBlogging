package com.example.apuser.mymicroblogging.domain.repository.api.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by apuser on 4/23/15.
 */
@Root(strict=false, name="user")
public class User {
    @Element(name="name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

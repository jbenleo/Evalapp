package com.example.jbleo.evalapp.model;


import com.example.jbleo.evalapp.R;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by jbleo on 24/03/2017.
 */

public class TestTemplate implements Serializable{
    private String uuid;
    private String title;
    private String description;
    private long createdOn;
    private String type;
    private Map<String, String> users;

    public TestTemplate() {

    }

    public TestTemplate(String uuid, String title, String description, long createdOn, String type, Map<String, String> users) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.createdOn = createdOn;
        this.type = type;
        this.users = users;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public void setUsers(Map<String, String> users) {
        this.users = users;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

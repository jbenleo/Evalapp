package com.example.jbleo.evalapp.model;

import android.util.ArrayMap;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jbleo on 20/03/2017.
 */

public class Client implements Serializable{
    private UUID uuid;
    private String name;
    private String description;
    private long birthday;
    private Map<String, String> users;

    public Client(){

    }

    public Client(String name, String description, long birthday){
        this.name = name;
        this.description = description;
        this.birthday = birthday;
        this.users = new ArrayMap<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public Map<String,String> getUsers() {
        return users;
    }

    public void setUsers(Map<String,String> users) {
        this.users = users;
    }
}

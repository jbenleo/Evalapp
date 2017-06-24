package com.example.jbleo.evalapp.model;

import java.util.UUID;

/**
 * Created by jbleo on 18/03/2017.
 */

public class EvalTest {
    private UUID uuid;
    private String title;
    private String description;

    public EvalTest(String title, String description){
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.description = description;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
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
}

package com.example.jbleo.evalapp.model;

import java.util.List;
import java.util.Map;

/**
 * Created by jbleo on 26/03/2017.
 */

public class TextTestTemplate extends TestTemplate {
    private List<String> textList;

    public TextTestTemplate() {
    }

    public TextTestTemplate(String uuid, String title, String description, long createdOn, Map<String, String> users, List<String> textList) {
        super(uuid, title, description, createdOn, "TextTestTemplate", users);
        this.textList = textList;
    }

    public List<String> getTextList() {
        return textList;
    }

    public void setTextList(List<String> textList) {
        this.textList = textList;
    }
}

package org.example.model;

import lombok.Data;

@Data
public class ToDoList {

    private String title;
    private String description;

    public ToDoList(String title, String description) {
        this.title = title;
        this.description = description;
    }
}

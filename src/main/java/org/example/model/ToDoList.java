package org.example.model;

import lombok.Data;

@Data
public class ToDoList {

    private String title;
    private String description;
    private String state;

    public ToDoList(String title, String description, String state) {
        this.title = title;
        this.description = description;
        this.state = state;
    }
}

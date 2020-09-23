package com.asaitec.rest.model;

public class Comment {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "description='" + description + '\'' +
                '}';
    }
}

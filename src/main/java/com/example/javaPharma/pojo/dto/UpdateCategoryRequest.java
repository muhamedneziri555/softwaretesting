package com.example.javaPharma.pojo.dto;



public class UpdateCategoryRequest {
    private String name;

    public UpdateCategoryRequest(String name) {
        this.name = name;
    }

    public UpdateCategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

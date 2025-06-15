package com.example.javaPharma.pojo.dto;



public class CreateCategoryRequest {
    private String name;

    public CreateCategoryRequest(String name) {
        this.name = name;
    }

    public CreateCategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

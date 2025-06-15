package com.example.javaPharma.pojo.dto;

public class CreateManufacturerRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreateManufacturerRequest() {
    }

    public CreateManufacturerRequest(String name) {
        this.name = name;
    }
}


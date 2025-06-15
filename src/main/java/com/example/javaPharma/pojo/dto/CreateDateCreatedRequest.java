package com.example.javaPharma.pojo.dto;

public class CreateDateCreatedRequest {
    private String date;

    public CreateDateCreatedRequest(String date) {
        this.date = date;
    }

    public CreateDateCreatedRequest() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

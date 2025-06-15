package com.example.javaPharma.pojo.dto;

public class CreateExpiryDateRequest {
    private String date;

    public CreateExpiryDateRequest(String date) {
        this.date = date;
    }

    public CreateExpiryDateRequest() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

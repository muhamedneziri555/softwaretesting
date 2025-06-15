package com.example.javaPharma.pojo.dto;

public class CreateMedicineRequest {

    private String name;

    private Long categoryId;

    private Long manufacturerId;

    private Long dateCreatedId;

    private Long expiryDateId;

    public CreateMedicineRequest(String name, Long categoryId, Long manufacturerId, Long dateCreatedId, Long expiryDateId) {
        this.name = name;
        this.categoryId = categoryId;
        this.manufacturerId = manufacturerId;
        this.dateCreatedId = dateCreatedId;
        this.expiryDateId = expiryDateId;
    }

    public CreateMedicineRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Long getDateCreatedId() {
        return dateCreatedId;
    }

    public void setDateCreatedId(Long dateCreatedId) {
        this.dateCreatedId = dateCreatedId;
    }

    public Long getExpiryDateId() {
        return expiryDateId;
    }

    public void setExpiryDateId(Long expiryDateId) {
        this.expiryDateId = expiryDateId;
    }
}


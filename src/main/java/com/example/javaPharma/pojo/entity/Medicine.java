package com.example.javaPharma.pojo.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name= "medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("medicines")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    @JsonIgnoreProperties("medicines")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "date_created_id")
    @JsonIgnoreProperties("medicines")
    private DateCreated dateCreated;

    @ManyToOne
    @JoinColumn(name = "expiry_date_id")
    @JsonIgnoreProperties("medicines")
    private ExpiryDate expiryDate;

    public Medicine() {
    }

    public Medicine(Long id, String name, Category category, Manufacturer manufacturer, DateCreated dateCreated, ExpiryDate expiryDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.dateCreated = dateCreated;
        this.expiryDate = expiryDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public DateCreated getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(DateCreated dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ExpiryDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(ExpiryDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
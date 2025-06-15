package com.example.javaPharma.service;


import com.example.javaPharma.pojo.dto.CreateMedicineRequest;
import com.example.javaPharma.pojo.entity.Medicine;

import java.util.Date;
import java.util.List;

public interface MedicineService {
    List<Medicine> getAllMedicines();

    Medicine getMedicineById(Long id);

    List<Medicine> searchByName(String name);

    List<Medicine> searchByCategory(String category);

    List<Medicine> searchByManufacturer(String manufacturer);

    List<Medicine> searchByDateCreated(String dateCreated);

    List<Medicine> searchByExpiryDate(String expiryDate);

    Medicine saveMedicine(CreateMedicineRequest medicine);

    void deleteMedicine(Long id);
}
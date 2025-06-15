package com.example.javaPharma.service;


import com.example.javaPharma.pojo.dto.CreateMedicineRequest;
import com.example.javaPharma.pojo.entity.Manufacturer;
import com.example.javaPharma.pojo.entity.Medicine;
import com.example.javaPharma.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DateCreatedService dateCreatedService;
    @Autowired
    private ExpiryDateService expiryDateService;
    @Autowired
    private ManufacturerService manufacturerService;

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id).orElse(null);
    }

    @Override
    public List<Medicine> searchByName(String name) {
        return medicineRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Medicine> searchByCategory(String category) {
        List<Medicine> allMedicines = medicineRepository.findAll();
        return allMedicines.stream()
                .filter(medicine -> medicine.getCategory() != null && medicine.getCategory().getName().contains(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Medicine> searchByManufacturer(String manufacturer) {
        List<Medicine> allMedicines = medicineRepository.findAll();
        return allMedicines.stream()
                .filter(medicine -> medicine.getManufacturer() != null && medicine.getManufacturer().getName().contains(manufacturer))
                .collect(Collectors.toList());
    }

    @Override
    public List<Medicine> searchByDateCreated(String dateCreated) {
        List<Medicine> allMedicines = medicineRepository.findAll();
        return allMedicines.stream()
                .filter(medicine -> medicine.getDateCreated() != null && medicine.getDateCreated().getDate().equals(dateCreated))
                .collect(Collectors.toList());
    }

    @Override
    public List<Medicine> searchByExpiryDate(String expiryDate) {
        List<Medicine> allMedicines = medicineRepository.findAll();
        return allMedicines.stream()
                .filter(medicine -> medicine.getExpiryDate() != null && medicine.getExpiryDate().getDate().equals(expiryDate))
                .collect(Collectors.toList());
    }


    @Override
    public Medicine saveMedicine(CreateMedicineRequest medicine) {
        Medicine med = new Medicine();
        med.setName(medicine.getName());
        med.setCategory(categoryService.getCategoryById(medicine.getCategoryId()));
        med.setManufacturer(manufacturerService.getManufacturerById(medicine.getManufacturerId()));
        med.setExpiryDate(expiryDateService.getExpiryDateById(medicine.getExpiryDateId()));
        med.setDateCreated(dateCreatedService.getDateCreatedById(medicine.getDateCreatedId()));
        return medicineRepository.save(med);
    }

    @Override
    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }
}

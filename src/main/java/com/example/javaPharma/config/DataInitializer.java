package com.example.javaPharma.config;

import com.example.javaPharma.pojo.entity.*;
import com.example.javaPharma.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private DateCreatedRepository dateCreatedRepository;

    @Autowired
    private ExpiryDateRepository expiryDateRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public void run(String... args) {
        // Create 5 Categories
        Category antibiotics = new Category();
        antibiotics.setName("Antibiotics");
        categoryRepository.save(antibiotics);

        Category painRelief = new Category();
        painRelief.setName("Pain Relief");
        categoryRepository.save(painRelief);

        Category vitamins = new Category();
        vitamins.setName("Vitamins");
        categoryRepository.save(vitamins);

        Category antihistamines = new Category();
        antihistamines.setName("Antihistamines");
        categoryRepository.save(antihistamines);

        Category cardiovascular = new Category();
        cardiovascular.setName("Cardiovascular");
        categoryRepository.save(cardiovascular);

        // Create 5 Manufacturers
        Manufacturer pfizer = new Manufacturer();
        pfizer.setName("Pfizer");
        manufacturerRepository.save(pfizer);

        Manufacturer jnj = new Manufacturer();
        jnj.setName("Johnson & Johnson");
        manufacturerRepository.save(jnj);

        Manufacturer novartis = new Manufacturer();
        novartis.setName("Novartis");
        manufacturerRepository.save(novartis);

        Manufacturer merck = new Manufacturer();
        merck.setName("Merck");
        manufacturerRepository.save(merck);

        Manufacturer gsk = new Manufacturer();
        gsk.setName("GlaxoSmithKline");
        manufacturerRepository.save(gsk);

        // Create 5 Date Created entries
        DateCreated date1 = new DateCreated();
        date1.setDate("2024-01-01");
        dateCreatedRepository.save(date1);

        DateCreated date2 = new DateCreated();
        date2.setDate("2024-02-01");
        dateCreatedRepository.save(date2);

        DateCreated date3 = new DateCreated();
        date3.setDate("2024-03-01");
        dateCreatedRepository.save(date3);

        DateCreated date4 = new DateCreated();
        date4.setDate("2024-04-01");
        dateCreatedRepository.save(date4);

        DateCreated date5 = new DateCreated();
        date5.setDate("2024-05-01");
        dateCreatedRepository.save(date5);

        // Create 5 Expiry Dates
        ExpiryDate expiry1 = new ExpiryDate();
        expiry1.setDate("2025-01-01");
        expiryDateRepository.save(expiry1);

        ExpiryDate expiry2 = new ExpiryDate();
        expiry2.setDate("2025-02-01");
        expiryDateRepository.save(expiry2);

        ExpiryDate expiry3 = new ExpiryDate();
        expiry3.setDate("2025-03-01");
        expiryDateRepository.save(expiry3);

        ExpiryDate expiry4 = new ExpiryDate();
        expiry4.setDate("2025-04-01");
        expiryDateRepository.save(expiry4);

        ExpiryDate expiry5 = new ExpiryDate();
        expiry5.setDate("2025-05-01");
        expiryDateRepository.save(expiry5);

        // Create 5 Medicines
        Medicine amoxicillin = new Medicine();
        amoxicillin.setName("Amoxicillin 500mg");
        amoxicillin.setCategory(antibiotics);
        amoxicillin.setManufacturer(pfizer);
        amoxicillin.setDateCreated(date1);
        amoxicillin.setExpiryDate(expiry1);
        medicineRepository.save(amoxicillin);

        Medicine ibuprofen = new Medicine();
        ibuprofen.setName("Ibuprofen 400mg");
        ibuprofen.setCategory(painRelief);
        ibuprofen.setManufacturer(jnj);
        ibuprofen.setDateCreated(date2);
        ibuprofen.setExpiryDate(expiry2);
        medicineRepository.save(ibuprofen);

        Medicine vitaminC = new Medicine();
        vitaminC.setName("Vitamin C 1000mg");
        vitaminC.setCategory(vitamins);
        vitaminC.setManufacturer(novartis);
        vitaminC.setDateCreated(date3);
        vitaminC.setExpiryDate(expiry3);
        medicineRepository.save(vitaminC);

        Medicine cetirizine = new Medicine();
        cetirizine.setName("Cetirizine 10mg");
        cetirizine.setCategory(antihistamines);
        cetirizine.setManufacturer(merck);
        cetirizine.setDateCreated(date4);
        cetirizine.setExpiryDate(expiry4);
        medicineRepository.save(cetirizine);

        Medicine atorvastatin = new Medicine();
        atorvastatin.setName("Atorvastatin 20mg");
        atorvastatin.setCategory(cardiovascular);
        atorvastatin.setManufacturer(gsk);
        atorvastatin.setDateCreated(date5);
        atorvastatin.setExpiryDate(expiry5);
        medicineRepository.save(atorvastatin);
    }
} 
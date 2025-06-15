package com.example.javaPharma.controller;

import com.example.javaPharma.pojo.dto.CreateMedicineRequest;
import com.example.javaPharma.pojo.entity.*;
import com.example.javaPharma.repository.*;
import com.example.javaPharma.service.MedicineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MedicineControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private DateCreatedRepository dateCreatedRepository;

    @Autowired
    private ExpiryDateRepository expiryDateRepository;

    private Category category;
    private Manufacturer manufacturer;
    private DateCreated dateCreated;
    private ExpiryDate expiryDate;
    private Medicine testMedicine;

    @BeforeEach
    void setUp() {
        // Clear the database before each test
        medicineRepository.deleteAll();
        categoryRepository.deleteAll();
        manufacturerRepository.deleteAll();
        dateCreatedRepository.deleteAll();
        expiryDateRepository.deleteAll();

        // Create test data
        category = new Category();
        category.setName("Test Category");
        category = categoryRepository.save(category);

        manufacturer = new Manufacturer();
        manufacturer.setName("Test Manufacturer");
        manufacturer = manufacturerRepository.save(manufacturer);

        dateCreated = new DateCreated();
        dateCreated.setDate("2024-01-01");
        dateCreated = dateCreatedRepository.save(dateCreated);

        expiryDate = new ExpiryDate();
        expiryDate.setDate("2025-01-01");
        expiryDate = expiryDateRepository.save(expiryDate);

        // Create a test medicine
        testMedicine = new Medicine();
        testMedicine.setName("Test Medicine");
        testMedicine.setCategory(category);
        testMedicine.setManufacturer(manufacturer);
        testMedicine.setDateCreated(dateCreated);
        testMedicine.setExpiryDate(expiryDate);
        testMedicine = medicineRepository.save(testMedicine);
    }

    @Test
    void testCreateMedicine() throws Exception {
        // Create a new medicine using CreateMedicineRequest
        CreateMedicineRequest request = new CreateMedicineRequest(
            "New Test Medicine",
            category.getId(),
            manufacturer.getId(),
            dateCreated.getId(),
            expiryDate.getId()
        );

        MvcResult result = mockMvc.perform(post("/api/medicines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Test Medicine"))
                .andReturn();

        // Verify the medicine was saved
        String responseBody = result.getResponse().getContentAsString();
        Medicine savedMedicine = objectMapper.readValue(responseBody, Medicine.class);
        assertNotNull(savedMedicine.getId());
        assertEquals("New Test Medicine", savedMedicine.getName());
    }

    @Test
    void testGetAllMedicines() throws Exception {
        // Create another medicine
        CreateMedicineRequest request = new CreateMedicineRequest(
            "Another Test Medicine",
            category.getId(),
            manufacturer.getId(),
            dateCreated.getId(),
            expiryDate.getId()
        );
        medicineService.saveMedicine(request);

        mockMvc.perform(get("/api/medicines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Test Medicine"))
                .andExpect(jsonPath("$[1].name").value("Another Test Medicine"));
    }

    @Test
    void testGetMedicineById() throws Exception {
        mockMvc.perform(get("/api/medicines/{id}", testMedicine.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testMedicine.getId()))
                .andExpect(jsonPath("$.name").value("Test Medicine"))
                .andExpect(jsonPath("$.category.name").value("Test Category"))
                .andExpect(jsonPath("$.manufacturer.name").value("Test Manufacturer"));
    }

    @Test
    void testGetMedicineByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/medicines/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteMedicine() throws Exception {
        // First verify the medicine exists
        assertTrue(medicineRepository.existsById(testMedicine.getId()));

        // Delete the medicine
        mockMvc.perform(delete("/api/medicines/{id}", testMedicine.getId()))
                .andExpect(status().isOk());

        // Verify the medicine was deleted
        assertFalse(medicineRepository.existsById(testMedicine.getId()));
    }

    @Test
    void testDeleteMedicineNotFound() throws Exception {
        mockMvc.perform(delete("/api/medicines/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateMedicine() throws Exception {
        // Create update request
        CreateMedicineRequest updateRequest = new CreateMedicineRequest(
            "Updated Test Medicine",
            category.getId(),
            manufacturer.getId(),
            dateCreated.getId(),
            expiryDate.getId()
        );

        mockMvc.perform(put("/api/medicines/{id}", testMedicine.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Test Medicine"));

        // Verify the update in the database
        Medicine updatedMedicine = medicineRepository.findById(testMedicine.getId()).orElse(null);
        assertNotNull(updatedMedicine);
        assertEquals("Updated Test Medicine", updatedMedicine.getName());
    }

    @Test
    void testUpdateMedicineNotFound() throws Exception {
        CreateMedicineRequest updateRequest = new CreateMedicineRequest(
            "Updated Test Medicine",
            category.getId(),
            manufacturer.getId(),
            dateCreated.getId(),
            expiryDate.getId()
        );

        mockMvc.perform(put("/api/medicines/{id}", 999L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSearchMedicineByName() throws Exception {
        // Create another medicine with a different name
        CreateMedicineRequest request = new CreateMedicineRequest(
            "Special Test Medicine",
            category.getId(),
            manufacturer.getId(),
            dateCreated.getId(),
            expiryDate.getId()
        );
        medicineService.saveMedicine(request);

        mockMvc.perform(get("/api/medicines/search")
                .param("name", "Special"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Special Test Medicine"));
    }

    @Test
    void testSearchMedicineByManufacturer() throws Exception {
        mockMvc.perform(get("/api/medicines/searchManufacturer")
                .param("manufacturer", "Test Manufacturer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].manufacturer.name").value("Test Manufacturer"));
    }

    @Test
    void testSearchMedicineByDateCreated() throws Exception {
        mockMvc.perform(get("/api/medicines/searchDateCreated")
                .param("date", "2024-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].dateCreated.date").value("2024-01-01"));
    }

    @Test
    void testSearchMedicineByExpiryDate() throws Exception {
        mockMvc.perform(get("/api/medicines/searchExpiryDate")
                .param("expdate", "2025-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].expiryDate.date").value("2025-01-01"));
    }

    @Test
    void testCreateMedicineWithInvalidData() throws Exception {
        // Test with missing required fields
        CreateMedicineRequest invalidRequest = new CreateMedicineRequest();
        // Don't set any fields

        mockMvc.perform(post("/api/medicines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());

        // Test with null category ID
        invalidRequest.setName("Test Medicine");
        invalidRequest.setCategoryId(null);
        invalidRequest.setManufacturerId(manufacturer.getId());
        invalidRequest.setDateCreatedId(dateCreated.getId());
        invalidRequest.setExpiryDateId(expiryDate.getId());

        mockMvc.perform(post("/api/medicines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateMedicineWithNonExistentRelations() throws Exception {
        CreateMedicineRequest request = new CreateMedicineRequest(
            "Test Medicine",
            999L, // Non-existent category
            manufacturer.getId(),
            dateCreated.getId(),
            expiryDate.getId()
        );

        mockMvc.perform(post("/api/medicines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        // Test with non-existent manufacturer
        request.setCategoryId(category.getId());
        request.setManufacturerId(999L);

        mockMvc.perform(post("/api/medicines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testMedicineWithInvalidDates() throws Exception {
        // Create an expiry date before creation date
        DateCreated futureDate = new DateCreated();
        futureDate.setDate("2025-01-01");
        futureDate = dateCreatedRepository.save(futureDate);

        ExpiryDate pastDate = new ExpiryDate();
        pastDate.setDate("2024-01-01");
        pastDate = expiryDateRepository.save(pastDate);

        // Create request with invalid dates
        CreateMedicineRequest request = new CreateMedicineRequest(
            "Test Medicine",
            category.getId(),
            manufacturer.getId(),
            futureDate.getId(),  // Future creation date
            pastDate.getId()     // Past expiry date
        );

        mockMvc.perform(post("/api/medicines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetMedicinesByCategory() throws Exception {
        // Create another category
        Category anotherCategory = new Category();
        anotherCategory.setName("Another Category");
        anotherCategory = categoryRepository.save(anotherCategory);

        // Create medicine with another category
        Medicine anotherMedicine = new Medicine();
        anotherMedicine.setName("Another Test Medicine");
        anotherMedicine.setCategory(anotherCategory);
        anotherMedicine.setManufacturer(manufacturer);
        anotherMedicine.setDateCreated(dateCreated);
        anotherMedicine.setExpiryDate(expiryDate);
        medicineRepository.save(anotherMedicine);

        // Test getting medicines by category using the search endpoint
        mockMvc.perform(get("/api/medicines/search")
                .param("category", "Test Category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].category.name").value("Test Category"));

        mockMvc.perform(get("/api/medicines/search")
                .param("category", "Another Category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].category.name").value("Another Category"));
    }

    @Test
    void testGetMedicinesByManufacturer() throws Exception {
        // Create another manufacturer
        Manufacturer anotherManufacturer = new Manufacturer();
        anotherManufacturer.setName("Another Manufacturer");
        anotherManufacturer = manufacturerRepository.save(anotherManufacturer);

        // Create medicine with another manufacturer
        Medicine anotherMedicine = new Medicine();
        anotherMedicine.setName("Another Test Medicine");
        anotherMedicine.setCategory(category);
        anotherMedicine.setManufacturer(anotherManufacturer);
        anotherMedicine.setDateCreated(dateCreated);
        anotherMedicine.setExpiryDate(expiryDate);
        medicineRepository.save(anotherMedicine);

        // Test getting medicines by manufacturer
        mockMvc.perform(get("/api/medicines/manufacturer/{manufacturerId}", manufacturer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].manufacturer.name").value("Test Manufacturer"));

        mockMvc.perform(get("/api/medicines/manufacturer/{manufacturerId}", anotherManufacturer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].manufacturer.name").value("Another Manufacturer"));
    }

    @Test
    void testUpdateMedicineWithInvalidData() throws Exception {
        // Test updating with empty name
        testMedicine.setName("");

        mockMvc.perform(put("/api/medicines/{id}", testMedicine.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMedicine)))
                .andExpect(status().isBadRequest());

        // Test updating with very long name
        testMedicine.setName("a".repeat(256));

        mockMvc.perform(put("/api/medicines/{id}", testMedicine.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMedicine)))
                .andExpect(status().isBadRequest());

        // Test updating with null category
        testMedicine.setName("Valid Name");
        testMedicine.setCategory(null);

        mockMvc.perform(put("/api/medicines/{id}", testMedicine.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMedicine)))
                .andExpect(status().isBadRequest());
    }
} 
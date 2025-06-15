package com.example.javaPharma.controller;

import com.example.javaPharma.pojo.dto.CreateMedicineRequest;
import com.example.javaPharma.pojo.entity.Medicine;
import com.example.javaPharma.service.MedicineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@Tag(name = "Medicine Management", description = "APIs for managing medicines and their details")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @Operation(summary = "Get all medicines", description = "Retrieves a list of all medicines in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all medicines"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<Medicine> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    @Operation(summary = "Get medicine by ID", description = "Retrieves a specific medicine by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the medicine"),
        @ApiResponse(responseCode = "404", description = "Medicine not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public Medicine getMedicineById(
            @Parameter(description = "ID of the medicine to retrieve", required = true)
            @PathVariable Long id) {
        return medicineService.getMedicineById(id);
    }

    @Operation(summary = "Search medicines by name", description = "Search for medicines by their name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully found matching medicines"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/search")
    public List<Medicine> searchMedicineByName(
            @Parameter(description = "Name to search for", required = true)
            @RequestParam String name) {
        return medicineService.searchByName(name);
    }

    @Operation(summary = "Search medicines by manufacturer", description = "Search for medicines by their manufacturer")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully found matching medicines"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/searchManufacturer")
    public List<Medicine> searchByManufacturer(
            @Parameter(description = "Manufacturer name to search for", required = true)
            @RequestParam String manufacturer) {
        return medicineService.searchByManufacturer(manufacturer);
    }

    @Operation(summary = "Search medicines by creation date", description = "Search for medicines by their creation date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully found matching medicines"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/searchDateCreated")
    public List<Medicine> searchByDateCreated(
            @Parameter(description = "Creation date to search for", required = true)
            @RequestParam String date) {
        return medicineService.searchByDateCreated(date);
    }

    @Operation(summary = "Search medicines by expiry date", description = "Search for medicines by their expiry date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully found matching medicines"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/searchExpiryDate")
    public List<Medicine> searchByExpiryDate(
            @Parameter(description = "Expiry date to search for", required = true)
            @RequestParam String expdate) {
        return medicineService.searchByExpiryDate(expdate);
    }

    @Operation(summary = "Create new medicine", description = "Creates a new medicine with all its details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created the medicine"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public Medicine createMedicine(
            @Parameter(description = "Medicine details including category, manufacturer, and dates", required = true)
            @RequestBody CreateMedicineRequest medicine) {
        return medicineService.saveMedicine(medicine);
    }

    @Operation(summary = "Update medicine", description = "Updates an existing medicine by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated the medicine"),
        @ApiResponse(responseCode = "404", description = "Medicine not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public Medicine updateMedicine(
            @Parameter(description = "ID of the medicine to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated medicine details", required = true)
            @RequestBody CreateMedicineRequest medicine) {
        var existingMedicine = medicineService.getMedicineById(id);
        existingMedicine.setName(medicine.getName());
        return medicineService.saveMedicine(medicine);
    }

    @Operation(summary = "Delete medicine", description = "Deletes a medicine by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted the medicine"),
        @ApiResponse(responseCode = "404", description = "Medicine not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void deleteMedicine(
            @Parameter(description = "ID of the medicine to delete", required = true)
            @PathVariable Long id) {
        medicineService.deleteMedicine(id);
    }
}

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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        return ResponseEntity.ok(medicines);
    }

    @Operation(summary = "Get medicine by ID", description = "Retrieves a specific medicine by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the medicine"),
        @ApiResponse(responseCode = "404", description = "Medicine not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(
            @Parameter(description = "ID of the medicine to retrieve", required = true)
            @PathVariable Long id) {
        Medicine medicine = medicineService.getMedicineById(id);
        return ResponseEntity.ok(medicine);
    }

    @Operation(summary = "Search medicines", description = "Search for medicines by name or category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully found matching medicines"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/search")
    public ResponseEntity<List<Medicine>> searchMedicine(
            @Parameter(description = "Name to search for", required = false)
            @RequestParam(required = false) String name,
            @Parameter(description = "Category to search for", required = false)
            @RequestParam(required = false) String category) {
        List<Medicine> medicines;
        if (name != null && !name.isEmpty()) {
            medicines = medicineService.searchByName(name);
        } else if (category != null && !category.isEmpty()) {
            medicines = medicineService.searchByCategory(category);
        } else {
            medicines = medicineService.getAllMedicines();
        }
        return ResponseEntity.ok(medicines);
    }

    @Operation(summary = "Search medicines by manufacturer", description = "Search for medicines by their manufacturer")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully found matching medicines"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/searchManufacturer")
    public ResponseEntity<List<Medicine>> searchByManufacturer(
            @Parameter(description = "Manufacturer name to search for", required = true)
            @RequestParam String manufacturer) {
        List<Medicine> medicines = medicineService.searchByManufacturer(manufacturer);
        return ResponseEntity.ok(medicines);
    }

    @Operation(summary = "Search medicines by creation date", description = "Search for medicines by their creation date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully found matching medicines"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/searchDateCreated")
    public ResponseEntity<List<Medicine>> searchByDateCreated(
            @Parameter(description = "Creation date to search for", required = true)
            @RequestParam String date) {
        List<Medicine> medicines = medicineService.searchByDateCreated(date);
        return ResponseEntity.ok(medicines);
    }

    @Operation(summary = "Search medicines by expiry date", description = "Search for medicines by their expiry date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully found matching medicines"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/searchExpiryDate")
    public ResponseEntity<List<Medicine>> searchByExpiryDate(
            @Parameter(description = "Expiry date to search for", required = true)
            @RequestParam String expdate) {
        List<Medicine> medicines = medicineService.searchByExpiryDate(expdate);
        return ResponseEntity.ok(medicines);
    }

    @Operation(summary = "Create new medicine", description = "Creates a new medicine with all its details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created the medicine"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Medicine> createMedicine(
            @Parameter(description = "Medicine details including category, manufacturer, and dates", required = true)
            @RequestBody CreateMedicineRequest medicine) {
        Medicine createdMedicine = medicineService.saveMedicine(medicine);
        return ResponseEntity.ok(createdMedicine);
    }

    @Operation(summary = "Update medicine", description = "Updates an existing medicine by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated the medicine"),
        @ApiResponse(responseCode = "404", description = "Medicine not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(
            @Parameter(description = "ID of the medicine to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated medicine details", required = true)
            @RequestBody CreateMedicineRequest medicine) {
        Medicine updatedMedicine = medicineService.updateMedicine(id, medicine);
        return ResponseEntity.ok(updatedMedicine);
    }

    @Operation(summary = "Delete medicine", description = "Deletes a medicine by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successfully deleted the medicine"),
        @ApiResponse(responseCode = "404", description = "Medicine not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(
            @Parameter(description = "ID of the medicine to delete", required = true)
            @PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.noContent().build();
    }
}

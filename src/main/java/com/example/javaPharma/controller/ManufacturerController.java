package com.example.javaPharma.controller;

import com.example.javaPharma.pojo.dto.CreateManufacturerRequest;
import com.example.javaPharma.pojo.entity.Manufacturer;
import com.example.javaPharma.service.ManufacturerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
@Tag(name = "Manufacturer Management", description = "APIs for managing medicine manufacturers")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @Operation(summary = "Get all manufacturers", description = "Retrieves a list of all medicine manufacturers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all manufacturers"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerService.getAllManufacturers();
    }

    @Operation(summary = "Get manufacturer by ID", description = "Retrieves a specific manufacturer by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the manufacturer"),
        @ApiResponse(responseCode = "404", description = "Manufacturer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public Manufacturer getManufacturerById(
            @Parameter(description = "ID of the manufacturer to retrieve", required = true)
            @PathVariable Long id) {
        return manufacturerService.getManufacturerById(id);
    }

    @Operation(summary = "Create new manufacturer", description = "Creates a new medicine manufacturer")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created the manufacturer"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public Manufacturer createManufacturer(
            @Parameter(description = "Manufacturer details", required = true)
            @RequestBody CreateManufacturerRequest manufacturer) {
        return manufacturerService.saveManufacturer(manufacturer);
    }

    @Operation(summary = "Delete manufacturer", description = "Deletes a manufacturer by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted the manufacturer"),
        @ApiResponse(responseCode = "404", description = "Manufacturer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void deleteManufacturer(
            @Parameter(description = "ID of the manufacturer to delete", required = true)
            @PathVariable Long id) {
        manufacturerService.deleteManufacturer(id);
    }
}

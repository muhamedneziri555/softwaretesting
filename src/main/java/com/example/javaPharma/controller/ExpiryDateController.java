package com.example.javaPharma.controller;

import com.example.javaPharma.pojo.dto.CreateExpiryDateRequest;
import com.example.javaPharma.pojo.entity.ExpiryDate;
import com.example.javaPharma.service.ExpiryDateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expirydates")
@Tag(name = "Expiry Date Management", description = "APIs for managing medicine expiry dates")
public class ExpiryDateController {

    @Autowired
    private ExpiryDateService expiryDateService;

    @Operation(summary = "Get all expiry dates", description = "Retrieves a list of all medicine expiry dates")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all expiry dates"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<ExpiryDate> getAllExpiryDates() {
        return expiryDateService.getAllExpiryDates();
    }

    @Operation(summary = "Get expiry date by ID", description = "Retrieves a specific expiry date by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the expiry date"),
        @ApiResponse(responseCode = "404", description = "Expiry date not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ExpiryDate getExpiryDateById(
            @Parameter(description = "ID of the expiry date to retrieve", required = true)
            @PathVariable Long id) {
        return expiryDateService.getExpiryDateById(id);
    }

    @Operation(summary = "Create new expiry date", description = "Creates a new medicine expiry date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created the expiry date"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ExpiryDate createExpiryDate(
            @Parameter(description = "Expiry date details", required = true)
            @RequestBody CreateExpiryDateRequest expiryDate) {
        return expiryDateService.saveExpiryDate(expiryDate);
    }

    @Operation(summary = "Delete expiry date", description = "Deletes an expiry date by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted the expiry date"),
        @ApiResponse(responseCode = "404", description = "Expiry date not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void deleteExpiryDate(
            @Parameter(description = "ID of the expiry date to delete", required = true)
            @PathVariable Long id) {
        expiryDateService.deleteExpiryDate(id);
    }
}

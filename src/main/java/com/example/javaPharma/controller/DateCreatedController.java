package com.example.javaPharma.controller;

import com.example.javaPharma.pojo.dto.CreateDateCreatedRequest;
import com.example.javaPharma.pojo.entity.DateCreated;
import com.example.javaPharma.service.DateCreatedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datecreated")
@Tag(name = "Date Created Management", description = "APIs for managing medicine creation dates")
public class DateCreatedController {

    @Autowired
    private DateCreatedService dateCreatedService;

    @Operation(summary = "Get all creation dates", description = "Retrieves a list of all medicine creation dates")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all creation dates"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<DateCreated> getAllDateCreated() {
        return dateCreatedService.getAllDateCreated();
    }

    @Operation(summary = "Get creation date by ID", description = "Retrieves a specific creation date by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the creation date"),
        @ApiResponse(responseCode = "404", description = "Creation date not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public DateCreated getDateCreatedById(
            @Parameter(description = "ID of the creation date to retrieve", required = true)
            @PathVariable Long id) {
        return dateCreatedService.getDateCreatedById(id);
    }

    @Operation(summary = "Create new creation date", description = "Creates a new medicine creation date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created the date"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public DateCreated createDateCreated(
            @Parameter(description = "Creation date details", required = true)
            @RequestBody CreateDateCreatedRequest dateCreated) {
        return dateCreatedService.saveDateCreated(dateCreated);
    }

    @Operation(summary = "Delete creation date", description = "Deletes a creation date by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted the creation date"),
        @ApiResponse(responseCode = "404", description = "Creation date not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void deleteDateCreated(
            @Parameter(description = "ID of the creation date to delete", required = true)
            @PathVariable Long id) {
        dateCreatedService.deleteDateCreated(id);
    }
}

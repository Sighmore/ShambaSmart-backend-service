package com.ShambaSmart.ShambaSmart.controller;

import com.ShambaSmart.ShambaSmart.dto.farmerDto;
import com.ShambaSmart.ShambaSmart.model.ApiResponse;
import com.ShambaSmart.ShambaSmart.model.Farmer;
import com.ShambaSmart.ShambaSmart.service.FarmerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmers")
@Tag(name = "Farmer Management", description = "APIs for managing farmers")
public class farmerController {


    // logger
    private static final Logger logger = LoggerFactory.getLogger(farmerController.class);

    // dependency injection of the FarmerService
    @Autowired
    private FarmerService farmerService;

    // Method to retrieve a farmer by ID
    @Operation(summary = "Fetch a farmer by ID", description = "Retrieves a farmer by their ID")
    @GetMapping("/fetch/{id}")
    public Farmer getFarmerById(Long id){
        logger.info("Fetching farmer with ID: {}", id);
        return   farmerService.getFarmerById(id);
    }

    // Method to retrieve all farmers
    @Operation(summary = "Fetch all farmers", description = "Retrieves a list of all farmers")
    @GetMapping("/fetch")
    public ResponseEntity<ApiResponse<List<farmerDto>>> getAllFarmers() {
        List<farmerDto> farmers = farmerService.getAllFarmers();
        logger.info("Fetching all farmers, total count: {}", farmers.size());
        return ResponseEntity.ok(new ApiResponse<>("List of all farmers", farmers, true));
    }

    // Method to create a new farmer then craft a response utilizing the API response model
    @Operation(summary = "Create a new farmer", description = "Creates a new farmer and returns the created farmer object")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Farmer>> createFarmer(@RequestBody @Valid farmerDto farmerDTO) {
        Farmer farmer = farmerService.CreateFarmer(farmerDTO);
        logger.info("Farmer created with Email: {}", farmer.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Farmer created successfully", farmer, true));
    }


    // Method to update a farmer's information
    @Operation(summary = "Update an existing farmer", description = "Updates the details of an existing farmer and returns the updated farmer object")
    @PostMapping("/update")
    public ResponseEntity<ApiResponse<Farmer>> updateFarmer(@RequestBody @Valid farmerDto farmerDto) {
        Farmer updatedFarmer = farmerService.updateFarmer(farmerDto);
        logger.info("Farmer updated with Email: {}", updatedFarmer.getEmail());
        return ResponseEntity.ok(new ApiResponse<>("Farmer updated successfully", updatedFarmer, true));
    }

    // Method to delete a farmer by ID
    @Operation(summary = "Delete a farmer", description = "Deletes a farmer by ID and returns a success message")
    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
        logger.info("Farmer deleted with ID: {}", id);
        return ResponseEntity.ok(new ApiResponse<>("Farmer deleted successfully", "Deleted farmer with ID: " + id, true));
    }

}

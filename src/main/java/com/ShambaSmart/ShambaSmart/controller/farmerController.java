package com.ShambaSmart.ShambaSmart.controller;

import com.ShambaSmart.ShambaSmart.model.Farmer;
import com.ShambaSmart.ShambaSmart.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class farmerController {

    // dependency injection of the FarmerService
    @Autowired
    private FarmerService farmerService;

    // Method to retrieve a farmer by ID
    @GetMapping("/farmer/{id}")
    public Farmer getFarmerById(Long id){
        return   farmerService.getFarmerById(id);
    }

    // Method to retrieve all farmers
    @GetMapping("/farmers")
    public List<Farmer> getAllFarmers() {
        return farmerService.getAllFarmers();
    }

    @PostMapping("/farmers")
    public Farmer createFarmer(Farmer farmer) {
        return farmerService.createFarmer(farmer);
    }


    // Method to update a farmer's information
    @PostMapping("/farmers/update")
    public Farmer updateFarmer(Farmer farmer) {
        return farmerService.updateFarmer(farmer);
    }

    // Method to delete a farmer by ID
    @PostMapping("/farmers/delete/{id}")
    public void deleteFarmer(Long id) {
        farmerService.deleteFarmer(id);
    }

}

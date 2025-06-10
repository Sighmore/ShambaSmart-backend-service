package com.ShambaSmart.ShambaSmart.service;


import com.ShambaSmart.ShambaSmart.model.Farmer;
import com.ShambaSmart.ShambaSmart.repository.FarmerRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmerService {

    //inject the FarmerRepository here
    @Autowired
    private FarmerRepository farmerRepository;

    /**
     * createFarmer method to save a farmer to the database
     * if the farmer already exists with the same phone number, it will throw an exception
     */
    public Farmer createFarmer(Farmer farmer) {
        if (farmerRepository.findAll().stream().anyMatch(f -> f.getPhoneNumber().equals(farmer.getPhoneNumber()))) {
            throw new IllegalArgumentException("Farmer with phone number " + farmer.getPhoneNumber() + " already exists.");
        }
        return farmerRepository.save(farmer);
    }

    /**
     * getFarmer if the farmer exists, otherwise return null
     */
    public Farmer getFarmerById(@NotNull Long id) {
        return farmerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Farmer with ID " + id + " does not exist."));
    }

    /**
     * deleteFarmer: first check if the farmer exists in the database
     *
     */
    public void deleteFarmer(Long id) {
        if (farmerRepository.findById(id).isPresent()) {
            farmerRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException("Farmer with ID " + id + " does not exist.");
        }
    }

    /**
     * getAllFarmers method to retrieve all farmers from the database
     */
    public List<Farmer> getAllFarmers() {
        if(farmerRepository.findAll().isEmpty()) {
            throw new IllegalArgumentException("No farmers found.");
        }else{
            return farmerRepository.findAll();
        }
    }

    /**
     * update farmer information
     * check if the farmer exists, if not return null
     */

    public Farmer updateFarmer(@NotNull Farmer farmer) {
        return farmerRepository.findById(farmer.getId())
                .map(existingFarmer -> farmerRepository.save(farmer))
                .orElseThrow(() -> new IllegalArgumentException("Farmer with ID " + farmer.getId() + " does not exist."));
    }


}

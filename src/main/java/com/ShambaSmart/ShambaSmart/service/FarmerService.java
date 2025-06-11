package com.ShambaSmart.ShambaSmart.service;


import aj.org.objectweb.asm.commons.Remapper;
import com.ShambaSmart.ShambaSmart.dto.farmerDto;
import com.ShambaSmart.ShambaSmart.model.Farmer;
import com.ShambaSmart.ShambaSmart.repository.FarmerRepository;
import jakarta.validation.Valid;
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
     * createFarmer method to save a farmer to the database.
     * Throws an exception if a farmer with the same email.
     */
    public Farmer CreateFarmer(@Valid @NotNull farmerDto farmerDTO) {
        if (farmerRepository.existsByEmail(farmerDTO.getEmail())) {
            throw new IllegalArgumentException("Farmer with email " + farmerDTO.getEmail() + " already exists.");
        }
        Farmer farmer = new Farmer();
        farmer.setName(farmerDTO.getName());
        farmer.setEmail(farmerDTO.getEmail());
        farmer.setPhoneNumber(farmerDTO.getPhoneNumber());
        farmer.setRegion(farmerDTO.getLocation());
        farmer.setFarmSize(farmerDTO.getFarmSize());
        farmer.setCropType(farmerDTO.getCropType());
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
   * The method uses the `stream` API to transform `Farmer` entities into `farmerDto` objects
   * for improved readability and maintainability. The `stream` approach aligns with functional
   * programming principles, ensuring immutability and declarative code. It also scales better
   * for processing large datasets compared to manual iteration. Throwing an exception when no
   * farmers are found enforces robust error handling, preventing invalid responses.
   */
    public List<farmerDto> getAllFarmers() {
        List<Farmer> farmers = farmerRepository.findAll();
        if (farmers.isEmpty()) {
            throw new IllegalArgumentException("No farmers found in the database.");
        }
        return farmers.stream()
                .map(farmer -> new farmerDto(
                        farmer.getName(),
                        farmer.getEmail(),
                        farmer.getPhoneNumber(),
                        farmer.getRegion(),
                        farmer.getFarmSize(),
                        farmer.getCropType()
                ))
                .toList();
    }



/**
 * Update farmer information using farmerDto.
 * Check if the farmer exists by email, if not throw an exception.
 */
public Farmer updateFarmer(@NotNull farmerDto farmerDTO) {
    Farmer existingFarmer = farmerRepository.findByEmail(farmerDTO.getEmail());
    if (existingFarmer == null) {
        throw new IllegalArgumentException("Farmer with email " + farmerDTO.getEmail() + " does not exist.");
    }
    existingFarmer.setName(farmerDTO.getName());
    existingFarmer.setPhoneNumber(farmerDTO.getPhoneNumber());
    existingFarmer.setRegion(farmerDTO.getLocation());
    existingFarmer.setFarmSize(farmerDTO.getFarmSize());
    existingFarmer.setCropType(farmerDTO.getCropType());

    return farmerRepository.save(existingFarmer);
}
}

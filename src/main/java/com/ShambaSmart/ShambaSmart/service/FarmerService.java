package com.ShambaSmart.ShambaSmart.service;


import com.ShambaSmart.ShambaSmart.dto.farmerDto;
import com.ShambaSmart.ShambaSmart.mapper.FarmerMapper;
import com.ShambaSmart.ShambaSmart.model.Farmer;
import com.ShambaSmart.ShambaSmart.repository.FarmerRepository;
import jakarta.validation.Valid;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

@Service
public class FarmerService {

    // Logger for logging information: SLF4J is used for logging in this service class
    private static final Logger logger = (Logger) LoggerFactory.getLogger(FarmerService.class);

    // DI of the FarmerRepository and FarmerMapper classes
    private final FarmerRepository farmerRepository;
    private final FarmerMapper farmerMapper;

    // It is used to inject the dependencies of FarmerRepository and FarmerMapper into the FarmerService class.
    public FarmerService(@Autowired FarmerRepository farmerRepository, @Autowired FarmerMapper farmerMapper) {
        this.farmerRepository = farmerRepository;
        this.farmerMapper = farmerMapper;
    }


    /**
     * createFarmer method to save a farmer to the database.
     * Throws an exception if a farmer with the same email.
     */
    public Farmer CreateFarmer(farmerDto farmerDTO) {
        if (farmerRepository.existsByEmail(farmerDTO.getEmail())) {
            throw new IllegalArgumentException("Farmer with email " + farmerDTO.getEmail() + " already exists.");
        }
        // Convert farmerDto to Farmer entity using the FarmerMapper and save it to the database
        logger.info("Creating farmer with email: {}");
        Farmer farmer = farmerMapper.toEntity(farmerDTO);
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
            logger.info("Deleting farmer with ID: {}");
            farmerRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException("Farmer with ID " + id + " does not exist.");
        }
    }

  /**
    * getAllFarmers: retrieves all farmers from the database.
   * If no farmers are found, it throws an exception.
   * This method utilizes Java Streams to convert List<Farmer> to List<farmerDto>
   * this approach is concise and efficient for transforming large data.
   */
    public List<farmerDto> getAllFarmers() {

        List<Farmer> farmers = farmerRepository.findAll();
        if (farmers.isEmpty()) {
            throw new IllegalArgumentException("No farmers found in the database.");
        }
        // Convert List<Farmer> to List<farmerDto> using Java Streams and the FarmerMapper
        return farmers.stream()
                .map(farmerMapper::toDto)
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
    // update the existing farmer's information with the new data from farmerDTO using the FarmerMapper
    existingFarmer.setName(farmerDTO.getName());
    existingFarmer.setPhoneNumber(farmerDTO.getPhoneNumber());
    existingFarmer.setRegion(farmerDTO.getLocation());
    existingFarmer.setFarmSize(farmerDTO.getFarmSize());
    existingFarmer.setCropType(farmerDTO.getCropType());
    // Save the updated farmer entity back to the database
    return farmerRepository.save(existingFarmer);
}
}

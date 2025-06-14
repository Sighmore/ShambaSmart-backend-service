package com.ShambaSmart.ShambaSmart.mapper;

import com.ShambaSmart.ShambaSmart.dto.farmerDto;
import com.ShambaSmart.ShambaSmart.model.Farmer;
import org.springframework.stereotype.Component;

@Component
public class FarmerMapper {


    //convert farmerDto to Farmer entity and vice versa
    public Farmer toEntity(farmerDto farmerDto) {
        if (farmerDto == null) {
            return null;
        }

        Farmer farmer = new Farmer();
        farmer.setName(farmerDto.getName());
        farmer.setEmail(farmerDto.getEmail());
        farmer.setPhoneNumber(farmerDto.getPhoneNumber());
        farmer.setRegion(farmerDto.getLocation());
        farmer.setFarmSize(farmerDto.getFarmSize());
        farmer.setCropType(farmerDto.getCropType());

        return farmer;
    }


    //convert Farmer entity to farmerDto
    public farmerDto toDto(Farmer farmer) {
        if (farmer == null) {
            return null;
        }

        farmerDto farmerDto = new farmerDto();
        farmerDto.setName(farmer.getName());
        farmerDto.setEmail(farmer.getEmail());
        farmerDto.setPhoneNumber(farmer.getPhoneNumber());
        farmerDto.setLocation(farmer.getRegion());
        farmerDto.setFarmSize(farmer.getFarmSize());
        farmerDto.setCropType(farmer.getCropType());

        return farmerDto;
    }
}

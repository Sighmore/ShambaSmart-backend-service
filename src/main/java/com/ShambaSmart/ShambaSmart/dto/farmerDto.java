package com.ShambaSmart.ShambaSmart.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class farmerDto {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{12}$", message = "Phone number must be between 10 and 15 digits")
    private String phoneNumber;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Farm size is required")
    private String farmSize;

    @NotBlank(message = "crop type is required")
    private String cropType;

    // Default constructor
    public farmerDto(String name, String email, String phoneNumber, String location, String farmSize, String cropType) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.farmSize = farmSize;
        this.cropType = cropType;
    }
}

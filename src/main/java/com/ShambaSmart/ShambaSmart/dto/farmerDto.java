package com.ShambaSmart.ShambaSmart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class farmerDto {

    /**
     * Data Transfer Object for Farmer
     * This class is used to transfer farmer data between layers.
     * It includes validation annotations to ensure that the data is valid before processing.
     */

    @NotBlank(message = "Name is required")
    @Schema(description = "Name of the farmer", example = "William Ruto")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Schema(description = "Email of the farmer", example = "william@griculture.com")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{12}$", message = "Phone number must be between 10 and 15 digits")
    @Schema(description = "Phone number of the farmer", example = "+254712345678")
    private String phoneNumber;

    @NotBlank(message = "Location is required")
    @Schema(description = "Location of the farmer", example = "Nairobi, Kenya")
    private String location;

    @NotBlank(message = "Farm size is required")
    @Schema(description = "Size of the farm in acres", example = "5 acres")
    private String farmSize;

    @NotBlank(message = "crop type is required")
    @Schema(description = "Type of crops grown by the farmer", example = "Maize, Beans")
    private String cropType;


}

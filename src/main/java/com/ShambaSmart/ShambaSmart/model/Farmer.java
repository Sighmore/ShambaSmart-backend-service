package com.ShambaSmart.ShambaSmart.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "farmers")
public class Farmer {

    // Getters and Setters
    @Id

    @SequenceGenerator(
        name = "farmer_sequence",
        sequenceName = "farmer_sequence",
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "farmer_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String region;
    private String farmSize;
    private String cropType;

    // Default constructor
    public Farmer() {
    }

    // Parameterized constructor
    public Farmer(String name, String email, String phoneNumber, String region, String farmSize, String cropType) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.region = region;
        this.farmSize = farmSize;
        this.cropType = cropType;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Farmer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", region='" + region + '\'' +
                ", farmSize='" + farmSize + '\'' +
                ", cropType='" + cropType + '\'' +
                '}';
    }


}

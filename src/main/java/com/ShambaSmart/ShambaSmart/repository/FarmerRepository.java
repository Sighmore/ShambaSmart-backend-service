package com.ShambaSmart.ShambaSmart.repository;

import com.ShambaSmart.ShambaSmart.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    //find a farmer by ID

}

package com.virtusa.epasscovid19.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.epasscovid19.models.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    
}

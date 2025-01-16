package com.example.Surveillance.Repositories;

import com.example.Surveillance.Entities.Surveillance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveillanceRepository extends JpaRepository<Surveillance, Long> {
    // Custom query methods can be added here if needed
}

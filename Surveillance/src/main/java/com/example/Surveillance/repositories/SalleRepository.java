package com.example.Surveillance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Surveillance.entities.Salle;

public interface SalleRepository extends JpaRepository<Salle, Long> {
    
}

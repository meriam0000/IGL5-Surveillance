package com.example.Surveillance.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Surveillance.Entities.Salle;

public interface SalleRepository extends JpaRepository<Salle, Long> {
    
}

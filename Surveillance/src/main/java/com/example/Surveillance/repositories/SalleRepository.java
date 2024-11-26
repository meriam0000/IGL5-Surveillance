package com.example.Surveillance.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Surveillance.Entities.Salle;

public interface SalleRepository extends JpaRepository<Salle, Long> {
    Page<Salle> findAll(Pageable pageable);
}

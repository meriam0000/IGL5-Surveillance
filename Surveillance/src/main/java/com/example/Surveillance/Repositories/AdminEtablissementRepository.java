package com.example.Surveillance.Repositories;

import com.example.Surveillance.Entities.AdminEtablissement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminEtablissementRepository extends JpaRepository<AdminEtablissement, Long> {
    Optional<AdminEtablissement> findByUsername(String username);
    // Additional queries if needed
}


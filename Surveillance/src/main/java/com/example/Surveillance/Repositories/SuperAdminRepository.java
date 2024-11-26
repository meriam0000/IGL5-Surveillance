package com.example.Surveillance.Repositories;

import com.example.Surveillance.Entities.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {
    Optional<SuperAdmin> findByUsername(String username);
    // Additional queries if needed
}

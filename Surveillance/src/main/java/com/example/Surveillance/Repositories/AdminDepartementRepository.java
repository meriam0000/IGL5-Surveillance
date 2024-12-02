package com.example.Surveillance.Repositories;

import com.example.Surveillance.Entities.AdminDepartement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminDepartementRepository extends JpaRepository<AdminDepartement,Long> {

}

package com.example.Surveillance.Repositories;

import com.example.Surveillance.Entities.AdminDepartement;
import com.example.Surveillance.Entities.Departement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {


}

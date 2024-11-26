package com.example.Surveillance.Repositories;

import com.example.Surveillance.Entities.Etablissement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {
    Page<Etablissement> findAll(Pageable pageable);
}


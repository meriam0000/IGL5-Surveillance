package com.example.Surveillance.Repositories;

import com.example.Surveillance.Entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepository  extends JpaRepository<Enseignant, Long> {

}

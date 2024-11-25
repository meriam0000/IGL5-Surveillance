package com.example.Surveillance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Surveillance.repositories.SalleRepository;
import com.example.Surveillance.entities.Salle;
import java.util.List;
import java.util.Optional;

@Service
public class SalleService {

    @Autowired
    private SalleRepository salleRepository;
    
    public List<Salle> getSalles() {
        return salleRepository.findAll();
    }

    public Optional<Salle> getAllSalle(Long id) {
        return salleRepository.findById(id);
    }

    public Salle createSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    public Salle updateSalle(Long id, Salle salleDetails) {
        Salle salle = salleRepository.findById(id).orElseThrow(() -> new RuntimeException("Salle not found"));
        salle.setNom(salleDetails.getNom());
        salle.setType(salleDetails.getType());
        salle.setCapacite(salleDetails.getCapacite());
        salle.setDepartement(salleDetails.getDepartement());
        return salleRepository.save(salle);
    }

    public boolean deleteSalle(Long id) {
        if (!salleRepository.existsById(id)) {
            return false;
        }
        salleRepository.deleteById(id);
        return true;
    }
}

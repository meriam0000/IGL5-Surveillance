package com.example.Surveillance.controllers;

import com.example.Surveillance.entities.Salle;
import com.example.Surveillance.services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;




@RestController
@RequestMapping("/salles")
public class SalleController {

    @Autowired
    private SalleService salleService;

    @GetMapping
    public List<Salle> getAllSalles() {
        return salleService.getSalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salle> getSalleById(@PathVariable Long id) {
        Salle salle = salleService.getAllSalle(id).get();
        if (salle != null) {
            return ResponseEntity.ok(salle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Salle createSalle(@RequestBody Salle salle) {
        return salleService.createSalle(salle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Salle> updateSalle(@PathVariable Long id, @RequestBody Salle salleDetails) {
        Salle updatedSalle = salleService.updateSalle(id, salleDetails);
        if (updatedSalle != null) {
            return ResponseEntity.ok(updatedSalle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalle(@PathVariable Long id) {
        boolean isDeleted = salleService.deleteSalle(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

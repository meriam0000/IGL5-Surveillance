package com.example.Surveillance.Controllers;

import com.example.Surveillance.Dtos.SurveillanceDTO;
import com.example.Surveillance.Services.SurveillanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/surveillances")
@AllArgsConstructor
public class SurveillanceController {

    private final SurveillanceService surveillanceService;

    @GetMapping
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT') or hasRole('ADMIN_DEPARTEMENT')")
    public ResponseEntity<List<SurveillanceDTO>> getAllSurveillances() {
        List<SurveillanceDTO> surveillances = surveillanceService.getAllSurveillances();
        return ResponseEntity.ok(surveillances);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT') or hasRole('ADMIN_DEPARTEMENT')")
    public ResponseEntity<SurveillanceDTO> getSurveillanceById(@PathVariable Long id) {
        SurveillanceDTO surveillance = surveillanceService.getSurveillanceById(id);
        return ResponseEntity.ok(surveillance);
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT') or hasRole('ADMIN_DEPARTEMENT')")
    public ResponseEntity<SurveillanceDTO> createSurveillance(@RequestBody SurveillanceDTO surveillanceDTO) {
        SurveillanceDTO createdSurveillance = surveillanceService.addSurveillance(surveillanceDTO);
        return ResponseEntity.ok(createdSurveillance);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT') or hasRole('ADMIN_DEPARTEMENT')")
    public ResponseEntity<SurveillanceDTO> updateSurveillance(@PathVariable Long id, @RequestBody SurveillanceDTO surveillanceDTO) {
        SurveillanceDTO updatedSurveillance = surveillanceService.updateSurveillance(id, surveillanceDTO);
        return ResponseEntity.ok(updatedSurveillance);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT') or hasRole('ADMIN_DEPARTEMENT')")
    public ResponseEntity<Void> deleteSurveillance(@PathVariable Long id) {
        surveillanceService.deleteSurveillance(id);
        return ResponseEntity.ok().build();
    }
}

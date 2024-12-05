package com.example.Surveillance.Controllers;

import com.example.Surveillance.Entities.AdminEtablissement;
import com.example.Surveillance.Services.AdminEtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adminEtablissements")
@PreAuthorize("hasRole('SUPERADMIN')")
public class AdminEtablissementController {

    @Autowired
    private AdminEtablissementService adminEtablissementService;

    // Create AdminEtablissement
    @PostMapping
    public ResponseEntity<AdminEtablissement> createAdminEtablissement(@RequestBody AdminEtablissement adminEtablissement) {
        AdminEtablissement createdAdminEtablissement = adminEtablissementService.saveAdminEtablissement(adminEtablissement);
        return new ResponseEntity<>(createdAdminEtablissement, HttpStatus.CREATED);
    }

    // Get All AdminEtablissements
    @GetMapping
    public ResponseEntity<List<AdminEtablissement>> getAllAdminEtablissements() {
        List<AdminEtablissement> adminEtablissements = adminEtablissementService.findAllAdminEtablissements();
        return new ResponseEntity<>(adminEtablissements, HttpStatus.OK);
    }

    // Get AdminEtablissement by ID
    @GetMapping("/{id}")
    public ResponseEntity<AdminEtablissement> getAdminEtablissementById(@PathVariable Long id) {
        AdminEtablissement adminEtablissement = adminEtablissementService.findAdminEtablissementById(id);
        if (adminEtablissement != null) {
            return new ResponseEntity<>(adminEtablissement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update AdminEtablissement
    @PutMapping("/{id}")
    public ResponseEntity<AdminEtablissement> updateAdminEtablissement(@PathVariable Long id, @RequestBody AdminEtablissement adminEtablissement) {
        AdminEtablissement updatedAdminEtablissement = adminEtablissementService.updateAdminEtablissement(id, adminEtablissement);
        return new ResponseEntity<>(updatedAdminEtablissement, HttpStatus.OK);
    }

    // Delete AdminEtablissement
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdminEtablissement(@PathVariable Long id) {
        adminEtablissementService.deleteAdminEtablissement(id);
        return new ResponseEntity<>("AdminEtablissement with id " + id + " deleted", HttpStatus.OK);
    }
}


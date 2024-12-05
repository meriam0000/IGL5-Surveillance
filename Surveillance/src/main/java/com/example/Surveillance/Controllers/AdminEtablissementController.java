package com.example.Surveillance.Controllers;

import com.example.Surveillance.Dtos.AdminEtablissementDto;
import com.example.Surveillance.Services.AdminEtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adminEtablissements")
public class AdminEtablissementController {

    @Autowired
    private AdminEtablissementService adminEtablissementService;

    // Create AdminEtablissementDto
    @PostMapping
    public ResponseEntity<AdminEtablissementDto> createAdminEtablissement(@RequestBody AdminEtablissementDto adminEtablissement) {
        AdminEtablissementDto createdAdminEtablissement = adminEtablissementService.saveAdminEtablissement(adminEtablissement);
        return new ResponseEntity<>(createdAdminEtablissement, HttpStatus.CREATED);
    }

    // Get All AdminEtablissements
    @GetMapping
    public ResponseEntity<List<AdminEtablissementDto>> getAllAdminEtablissements() {
        List<AdminEtablissementDto> adminEtablissements = adminEtablissementService.findAllAdminEtablissements();
        return new ResponseEntity<>(adminEtablissements, HttpStatus.OK);
    }

    // Get AdminEtablissementDto by ID
    @GetMapping("/{id}")
    public ResponseEntity<AdminEtablissementDto> getAdminEtablissementById(@PathVariable Long id) {
        AdminEtablissementDto adminEtablissement = adminEtablissementService.findAdminEtablissementById(id);
        if (adminEtablissement != null) {
            return new ResponseEntity<>(adminEtablissement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update AdminEtablissementDto
    @PutMapping("/{id}")
    public ResponseEntity<AdminEtablissementDto> updateAdminEtablissement(@PathVariable Long id, @RequestBody AdminEtablissementDto adminEtablissement) {
        AdminEtablissementDto updatedAdminEtablissement = adminEtablissementService.updateAdminEtablissement(id, adminEtablissement);
        return new ResponseEntity<>(updatedAdminEtablissement, HttpStatus.OK);
    }

    // Delete AdminEtablissementDto
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdminEtablissement(@PathVariable Long id) {
        adminEtablissementService.deleteAdminEtablissement(id);
        return new ResponseEntity<>("AdminEtablissementDto with id " + id + " deleted", HttpStatus.OK);
    }
}


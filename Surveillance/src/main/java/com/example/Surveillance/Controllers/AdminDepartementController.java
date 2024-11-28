package com.example.Surveillance.Controllers;


import com.example.Surveillance.Entities.AdminDepartement;
import com.example.Surveillance.Services.AdminDepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adminDepartements")
public class AdminDepartementController {

    @Autowired
    private AdminDepartementService adminDepartementService;

    // Create AdminDepartement

    @PostMapping
    public ResponseEntity<AdminDepartement> createAdminDepartement(@RequestBody AdminDepartement adminDepartement) {
        AdminDepartement createdAdminDepartement = adminDepartementService.saveAdminDepartement(adminDepartement);
        return new ResponseEntity<>(createdAdminDepartement, HttpStatus.CREATED);
    }

    // Get All AdminDepartements

    @GetMapping
    public ResponseEntity<List<AdminDepartement>> getAllAdminDepartements() {
        List<AdminDepartement> adminDepartements = adminDepartementService.findAllAdminDepartements();
        return new ResponseEntity<>(adminDepartements, HttpStatus.OK);
    }

    // Get AdminDepartement by ID

    @GetMapping("/{id}")
    public ResponseEntity<AdminDepartement> getAdminDepartementById(@PathVariable Long id) {
        AdminDepartement adminDepartement = adminDepartementService.findAdminDepartementById(id);
        if (adminDepartement != null) {
            return new ResponseEntity<>(adminDepartement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update AdminDepartement

    @PutMapping("/{id}")
    public ResponseEntity<AdminDepartement> updateAdminDepartement(@PathVariable Long id, @RequestBody AdminDepartement adminDepartement) {
        AdminDepartement updatedAdminDepartement = adminDepartementService.updateAdminDepartement(id, adminDepartement);
        return new ResponseEntity<>(updatedAdminDepartement, HttpStatus.OK);
    }

    // Delete AdminDepartement

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdminDepartement(@PathVariable Long id) {
        adminDepartementService.deleteAdminDepartement(id);
        return new ResponseEntity<>("AdminDepartement with id " + id + " deleted", HttpStatus.OK);
    }
}


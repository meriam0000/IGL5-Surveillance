package com.example.Surveillance.Controllers;


import com.example.Surveillance.Dtos.SuperAdminDto;
import com.example.Surveillance.Services.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/superAdmins")
public class SuperAdminController {

    @Autowired
    private SuperAdminService superAdminService;

    // Create SuperAdminDto
    @PostMapping
    public ResponseEntity<SuperAdminDto> createSuperAdmin(@RequestBody SuperAdminDto superAdmin) {
        SuperAdminDto createdSuperAdmin = superAdminService.saveSuperAdmin(superAdmin);
        return new ResponseEntity<>(createdSuperAdmin, HttpStatus.CREATED);
    }

    // Get All SuperAdmins
    @GetMapping
    public ResponseEntity<List<SuperAdminDto>> getAllSuperAdmins() {
        List<SuperAdminDto> superAdmins = superAdminService.findAllSuperAdmins();
        return new ResponseEntity<>(superAdmins, HttpStatus.OK);
    }

    // Get SuperAdminDto by ID
    @GetMapping("/{id}")
    public ResponseEntity<SuperAdminDto> getSuperAdminById(@PathVariable Long id) {
        SuperAdminDto superAdmin = superAdminService.findSuperAdminById(id);
        if (superAdmin != null) {
            return new ResponseEntity<>(superAdmin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update SuperAdminDto
    @PutMapping("/{id}")
    public ResponseEntity<SuperAdminDto> updateSuperAdmin(@PathVariable Long id, @RequestBody SuperAdminDto superAdmin) {
        SuperAdminDto updatedSuperAdmin = superAdminService.updateSuperAdmin(id, superAdmin);
        return new ResponseEntity<>(updatedSuperAdmin, HttpStatus.OK);
    }

    // Delete SuperAdminDto
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSuperAdmin(@PathVariable Long id) {
        superAdminService.deleteSuperAdmin(id);
        return new ResponseEntity<>("SuperAdminDto with id " + id + " deleted", HttpStatus.OK);
    }
}

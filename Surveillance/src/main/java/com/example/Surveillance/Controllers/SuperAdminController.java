package com.example.Surveillance.Controllers;


import com.example.Surveillance.Entities.SuperAdmin;
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

    // Create SuperAdmin
    @PostMapping
    public ResponseEntity<SuperAdmin> createSuperAdmin(@RequestBody SuperAdmin superAdmin) {
        SuperAdmin createdSuperAdmin = superAdminService.saveSuperAdmin(superAdmin);
        return new ResponseEntity<>(createdSuperAdmin, HttpStatus.CREATED);
    }

    // Get All SuperAdmins
    @GetMapping
    public ResponseEntity<List<SuperAdmin>> getAllSuperAdmins() {
        List<SuperAdmin> superAdmins = superAdminService.findAllSuperAdmins();
        return new ResponseEntity<>(superAdmins, HttpStatus.OK);
    }

    // Get SuperAdmin by ID
    @GetMapping("/{id}")
    public ResponseEntity<SuperAdmin> getSuperAdminById(@PathVariable Long id) {
        SuperAdmin superAdmin = superAdminService.findSuperAdminById(id);
        if (superAdmin != null) {
            return new ResponseEntity<>(superAdmin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update SuperAdmin
    @PutMapping("/{id}")
    public ResponseEntity<SuperAdmin> updateSuperAdmin(@PathVariable Long id, @RequestBody SuperAdmin superAdmin) {
        SuperAdmin updatedSuperAdmin = superAdminService.updateSuperAdmin(id, superAdmin);
        return new ResponseEntity<>(updatedSuperAdmin, HttpStatus.OK);
    }

    // Delete SuperAdmin
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSuperAdmin(@PathVariable Long id) {
        superAdminService.deleteSuperAdmin(id);
        return new ResponseEntity<>("SuperAdmin with id " + id + " deleted", HttpStatus.OK);
    }
}

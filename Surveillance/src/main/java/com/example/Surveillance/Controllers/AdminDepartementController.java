package com.example.Surveillance.Controllers;


import com.example.Surveillance.Dtos.AdminDepartementDto;
import com.example.Surveillance.Entities.AdminDepartement;
import com.example.Surveillance.Services.AdminDepartementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adminDepartements")
@AllArgsConstructor

public class AdminDepartementController {

    private final AdminDepartementService adminDepartementService;

    // Create AdminDepartement
    @PostMapping
    public ResponseEntity<AdminDepartementDto> createAdminDepartement(@RequestBody AdminDepartementDto adminDepartement) {
        AdminDepartementDto createdAdminDepartement = adminDepartementService.saveAdminDepartement(adminDepartement);
        return new ResponseEntity<>(createdAdminDepartement, HttpStatus.CREATED);
    }

    // Get All AdminDepartements
    @GetMapping
    public ResponseEntity<List<AdminDepartementDto>> getAllAdminDepartements() {
        List<AdminDepartementDto> adminDepartements = adminDepartementService.findAllAdminDepartements();
        return new ResponseEntity<>(adminDepartements, HttpStatus.OK);
    }

    // Get AdminDepartement by ID
    @GetMapping("/{id}")
    public ResponseEntity<AdminDepartementDto> getAdminDepartementById(@PathVariable Long id) {
        AdminDepartementDto adminDepartement = adminDepartementService.findAdminDepartementById(id);
       return new ResponseEntity<>(adminDepartement, HttpStatus.OK);
    }

    // Update AdminDepartement
    @PutMapping("/{id}")
    public ResponseEntity<AdminDepartementDto> updateAdminDepartement(
            @PathVariable Long id,
            @RequestBody AdminDepartementDto adminDepartementDto) {
        AdminDepartementDto updatedAdminDepartement = adminDepartementService.updateAdminDepartement(id, adminDepartementDto);
        return new ResponseEntity<>(updatedAdminDepartement, HttpStatus.OK);
    }

    // Delete AdminDepartement
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdminDepartement(@PathVariable Long id) {
        adminDepartementService.deleteAdminDepartement(id);
        return new ResponseEntity<>("AdminDepartement with id " + id + " deleted", HttpStatus.OK);
    }
}



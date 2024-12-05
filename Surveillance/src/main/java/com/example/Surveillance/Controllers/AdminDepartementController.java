package com.example.Surveillance.Controllers;


    import com.example.Surveillance.Dtos.AdminDepartementDto;
import com.example.Surveillance.Services.AdminDepartementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/AdminDepartementDtos")
@AllArgsConstructor
@PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT')")
public class AdminDepartementController {

    private final AdminDepartementService AdminDepartementService;

    // Create AdminDepartementDto
    @PostMapping
    public ResponseEntity<AdminDepartementDto> createAdminDepartementDto(@RequestBody AdminDepartementDto AdminDepartementDto) {
        AdminDepartementDto createdAdminDepartementDto = AdminDepartementService.saveAdminDepartement(AdminDepartementDto);
        return new ResponseEntity<>(createdAdminDepartementDto, HttpStatus.CREATED);
    }

    // Get All AdminDepartementDtos
    @GetMapping
    public ResponseEntity<List<AdminDepartementDto>> getAllAdminDepartementDtos() {
        List<AdminDepartementDto> AdminDepartementDtos = AdminDepartementService.findAllAdminDepartements();
        return new ResponseEntity<>(AdminDepartementDtos, HttpStatus.OK);
    }

    // Get AdminDepartementDto by ID
    @GetMapping("/{id}")
    public ResponseEntity<AdminDepartementDto> getAdminDepartementDtoById(@PathVariable Long id) {
        AdminDepartementDto AdminDepartementDto = AdminDepartementService.findAdminDepartementById(id);
        if (AdminDepartementDto != null) {
            return new ResponseEntity<>(AdminDepartementDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update AdminDepartementDto
    @PutMapping("/{id}")
    public ResponseEntity<AdminDepartementDto> updateAdminDepartementDto(
            @PathVariable Long id,
            @RequestBody AdminDepartementDto AdminDepartementDtoDto) {
        AdminDepartementDto updatedAdminDepartementDto = AdminDepartementService.updateAdminDepartement(id, AdminDepartementDtoDto);
        return new ResponseEntity<>(updatedAdminDepartementDto, HttpStatus.OK);
    }

    // Delete AdminDepartementDto
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdminDepartementDto(@PathVariable Long id) {
        AdminDepartementService.deleteAdminDepartement(id);
        return new ResponseEntity<>("AdminDepartementDto with id " + id + " deleted", HttpStatus.OK);
    }
}


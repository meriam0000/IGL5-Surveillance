package com.example.Surveillance.Controllers;

import com.example.Surveillance.Dtos.DepartementDto;
import com.example.Surveillance.Services.DepartementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RestController("/api/v1/departement")
@AllArgsConstructor
public class DepartementController {
        final private DepartementService departementService;

        @GetMapping
        @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT') or hasRole('ADMIN_DEPARTEMENT')")
        public ResponseEntity<List<DepartementDto>> findAll(Authentication authentication) {
            List<DepartementDto> departement = departementService.getAllDepartements(authentication);
            return ResponseEntity.ok(departement);
        }
        @PostMapping
        @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT')")
        public ResponseEntity<DepartementDto> addDepartement(@RequestBody DepartementDto DepartementDto) {
            return ResponseEntity.ok(departementService.addDepartement(DepartementDto));
        }

        @GetMapping("/{id}")
        @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT') or hasRole('ADMIN_DEPARTEMENT')")
        public ResponseEntity<DepartementDto> findById(@PathVariable Long id) {
            DepartementDto departement = departementService.getDepartementById(id);
            return ResponseEntity.ok(departement);
        }

        @PutMapping("/{id}")
        @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT') or hasRole('ADMIN_DEPARTEMENT')")
        public ResponseEntity<DepartementDto> UpdateDepartement(
                @PathVariable Long id,
                @RequestBody DepartementDto departementDto,
                Authentication authentication
        ) {
            DepartementDto updatedDepartement = departementService.updateDepartement(id, departementDto,authentication);
            return ResponseEntity.ok(updatedDepartement);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> remove(@PathVariable Long id) {
            departementService.deleteDepartement(id);
            return ResponseEntity.ok().build();
        }

    }

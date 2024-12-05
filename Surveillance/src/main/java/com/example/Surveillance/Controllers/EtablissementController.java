package com.example.Surveillance.Controllers;


import com.example.Surveillance.Dtos.EtablissementDto;
import com.example.Surveillance.Services.EtablissementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RestController
@RequestMapping("/api/v1/etablissement")
@AllArgsConstructor
public class EtablissementController {
    final private EtablissementService etablissementService;
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT')")
    @GetMapping
    public ResponseEntity<List<EtablissementDto>> findAll(Authentication authentication) {
        List<EtablissementDto> etablissement = etablissementService.getAllEtablissement(authentication);
        return ResponseEntity.ok(etablissement);
    }
    @PostMapping
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT')")
    public ResponseEntity<EtablissementDto> addEtablissement(@RequestBody EtablissementDto EtablissementDto) {
        return ResponseEntity.ok(etablissementService.addEtablissement(EtablissementDto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT')")
    public ResponseEntity<EtablissementDto> findById(@PathVariable Long id) {
        EtablissementDto etablissement = etablissementService.getEtablissementById(id);
        return ResponseEntity.ok(etablissement);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT')")
    public ResponseEntity<EtablissementDto> UpdateEnseignant(
            @PathVariable Long id,
            @RequestBody EtablissementDto etablissementDto
    ) {
        EtablissementDto updatedEtablissement = etablissementService.updateEtablissement(id, etablissementDto);
        return ResponseEntity.ok(updatedEtablissement);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT')")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        etablissementService.deleteEtablissement(id);
        return ResponseEntity.ok().build();
    }

}

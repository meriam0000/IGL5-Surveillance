package com.example.Surveillance.Controllers;


import com.example.Surveillance.Dtos.EtablissementDto;
import com.example.Surveillance.Services.EtablissementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController("/api/v1/etablissement")
@AllArgsConstructor
public class EtablissementController {
    final private EtablissementService etablissementService;

    @GetMapping
    public ResponseEntity<List<EtablissementDto>> findAll() {
        List<EtablissementDto> etablissement = etablissementService.getAllEtablissement();
        return ResponseEntity.ok(etablissement);
    }
    @PostMapping
    public ResponseEntity<EtablissementDto> addEtablissement(@RequestBody EtablissementDto EtablissementDto) {
        return ResponseEntity.ok(etablissementService.addEtablissement(EtablissementDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtablissementDto> findById(@PathVariable Long id) {
        EtablissementDto etablissement = etablissementService.getEtablissementById(id);
        return ResponseEntity.ok(etablissement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtablissementDto> UpdateEnseignant(
            @PathVariable Long id,
            @RequestBody EtablissementDto etablissementDto
    ) {
        EtablissementDto updatedEtablissement = etablissementService.updateEtablissement(id, etablissementDto);
        return ResponseEntity.ok(updatedEtablissement);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        etablissementService.deleteEtablissement(id);
        return ResponseEntity.ok().build();
    }

}

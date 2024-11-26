package com.example.Surveillance.Controllers;

import com.example.Surveillance.Dtos.EnseignantDto;
import com.example.Surveillance.Services.EnseignantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController("/api/v1/enseignants")
@AllArgsConstructor
public class EnseignantController {
    final private EnseignantService enseignantService;

    @GetMapping
    public ResponseEntity<List<EnseignantDto>> findAll() {
        List<EnseignantDto> enseignants = enseignantService.getAllEnseignants();
        return ResponseEntity.ok(enseignants);
    }
    @PostMapping
    public ResponseEntity<EnseignantDto> addEnseignant(@RequestBody EnseignantDto enseignantDto) {
        return ResponseEntity.ok(enseignantService.addEnseignant(enseignantDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnseignantDto> findById(@PathVariable Long id) {
        EnseignantDto enseignant = enseignantService.getEnseignantById(id);
        return ResponseEntity.ok(enseignant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnseignantDto> UpdateEnseignant(
            @PathVariable Long id,
            @RequestBody EnseignantDto enseignantDto
    ) {
        EnseignantDto updatedEnseignant = enseignantService.updateEnseignant(id, enseignantDto);
        return ResponseEntity.ok(updatedEnseignant);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        enseignantService.deleteEnseignant(id);
        return ResponseEntity.ok().build();
    }









}

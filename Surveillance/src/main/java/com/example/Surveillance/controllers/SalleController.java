package com.example.Surveillance.Controllers;

import com.example.Surveillance.Dtos.SalleDto;
import com.example.Surveillance.Services.SalleService;
import com.example.Surveillance.Util.PageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController("/api/v1/salles")
@AllArgsConstructor
public class SalleController {
    final private SalleService salleService;

    @GetMapping
    public ResponseEntity<PageResponse<SalleDto>> findAll(@RequestParam int page, @RequestParam int size) {
        PageResponse<SalleDto> salles = salleService.getAllSalles(page,size);
        return ResponseEntity.ok(salles);
    }
    @PostMapping
    public ResponseEntity<SalleDto> addSalle(@RequestBody SalleDto salleDto) {
        return ResponseEntity.ok(salleService.addSalle(salleDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalleDto> findById(@PathVariable Long id) {
        SalleDto salle = salleService.getSalleById(id);
        return ResponseEntity.ok(salle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalleDto> UpdateSalle(
            @PathVariable Long id,
            @RequestBody SalleDto salleDto
    ) {
        SalleDto updatedSalle = salleService.updateSalle(id, salleDto);
        return ResponseEntity.ok(updatedSalle);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        salleService.deleteSalle(id);
        return ResponseEntity.ok().build();
    }









}

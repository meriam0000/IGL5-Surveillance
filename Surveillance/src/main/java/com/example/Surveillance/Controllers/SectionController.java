package com.example.Surveillance.Controllers;
import com.example.Surveillance.Dtos.SectionDto;
import com.example.Surveillance.Services.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/Sections")
@AllArgsConstructor
public class SectionController {
    final private SectionService SectionService;

    @GetMapping
    public ResponseEntity<List<SectionDto>> findAll(Authentication authentication) {
        List<SectionDto> Sections = SectionService.getAllSections(authentication);
        return ResponseEntity.ok(Sections);
    }
    @PostMapping
    public ResponseEntity<SectionDto> addSection(@RequestBody SectionDto SectionDto) {
        return ResponseEntity.ok(SectionService.addSection(SectionDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionDto> findById(@PathVariable Long id) {
        SectionDto Section = SectionService.getSectionById(id);
        return ResponseEntity.ok(Section);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionDto> UpdateSection(
            @PathVariable Long id,
            @RequestBody SectionDto SectionDto
    ) {
        SectionDto updatedSection = SectionService.updateSection(id, SectionDto);
        return ResponseEntity.ok(updatedSection);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        SectionService.deleteSection(id);
        return ResponseEntity.ok().build();
    }

}

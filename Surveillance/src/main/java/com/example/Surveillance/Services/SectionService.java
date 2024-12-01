package com.example.Surveillance.Services;


import com.example.Surveillance.Dtos.SectionDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface SectionService {
    SectionDto addSection(SectionDto sectionDto);
    SectionDto getSectionById(Long id);
    SectionDto updateSection(Long id, SectionDto sectionDto);
    List<SectionDto> getAllSections(Authentication authentication);
    void deleteSalle(Long id);

}

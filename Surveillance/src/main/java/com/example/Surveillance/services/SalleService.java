package com.example.Surveillance.Services;



import com.example.Surveillance.Dtos.SalleDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface SalleService {
    SalleDto addSalle(SalleDto salleDto);
    SalleDto getSalleById(Long id);
    SalleDto updateSalle(Long id, SalleDto salleDto);
    List<SalleDto> getAllSalles(Authentication authentication);
    void deleteSalle(Long id);
}

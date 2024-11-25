package com.example.Surveillance.Services;

import java.util.List;

import com.example.Surveillance.Dtos.SalleDto;

public interface SalleService {
    SalleDto addSalle(SalleDto salleDto);
    SalleDto getSalleById(Long id);
    SalleDto updateSalle(Long id, SalleDto salleDto);
    List<SalleDto> getAllSalles();
    void deleteSalle(Long id);
}
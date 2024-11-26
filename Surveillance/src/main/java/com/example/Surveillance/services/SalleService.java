package com.example.Surveillance.Services;



import com.example.Surveillance.Dtos.SalleDto;
import com.example.Surveillance.Util.PageResponse;

public interface SalleService {
    SalleDto addSalle(SalleDto salleDto);
    SalleDto getSalleById(Long id);
    SalleDto updateSalle(Long id, SalleDto salleDto);
    PageResponse<SalleDto> getAllSalles(int page, int size);
    void deleteSalle(Long id);
}

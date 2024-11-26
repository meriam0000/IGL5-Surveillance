package com.example.Surveillance.Services;

import com.example.Surveillance.Dtos.DepartementDto;

import java.util.List;

public interface DepartementService {

    List<DepartementDto> getAllDepartements();

    DepartementDto addDepartement(DepartementDto departementDto);

    DepartementDto getDepartementById(Long id);


    DepartementDto updateDepartement(Long id, DepartementDto departementDto);

    void deleteDepartement(Long id);
}

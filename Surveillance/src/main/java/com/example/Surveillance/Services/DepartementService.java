package com.example.Surveillance.Services;

import com.example.Surveillance.Dtos.DepartementDto;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface DepartementService {

    List<DepartementDto> getAllDepartements( Authentication authentication);


    DepartementDto addDepartement(DepartementDto departementDto);

    DepartementDto getDepartementById(Long id);


    DepartementDto updateDepartement(Long id, DepartementDto departementDto, Authentication authentication);

    void deleteDepartement(Long id);

    List<DepartementDto> getDepartementsByEtablissementId(Long id);
}

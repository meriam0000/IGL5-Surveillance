package com.example.Surveillance.Services;

import com.example.Surveillance.Dtos.DepartementDto;
import com.example.Surveillance.Util.PageResponse;


public interface DepartementService {

    PageResponse<DepartementDto> getAllDepartements(int page , int size);

    DepartementDto addDepartement(DepartementDto departementDto);

    DepartementDto getDepartementById(Long id);


    DepartementDto updateDepartement(Long id, DepartementDto departementDto);

    void deleteDepartement(Long id);
}

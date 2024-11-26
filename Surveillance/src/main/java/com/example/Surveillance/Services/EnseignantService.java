package com.example.Surveillance.Services;


import com.example.Surveillance.Dtos.EnseignantDto;
import com.example.Surveillance.Util.PageResponse;


public interface EnseignantService {
    PageResponse<EnseignantDto> getAllEnseignants( int page, int size);

    EnseignantDto addEnseignant(EnseignantDto enseignantDto);

    EnseignantDto getEnseignantById(Long id);

    EnseignantDto updateEnseignant(Long id, EnseignantDto enseignantDto);

    void deleteEnseignant(Long id);
}

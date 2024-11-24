package com.example.Surveillance.Services;

import com.example.Surveillance.Dtos.EnseignantDto;

import java.util.List;

public interface EnseignantService {
    List<EnseignantDto> getAllEnseignants();

    EnseignantDto addEnseignant(EnseignantDto enseignantDto);

    EnseignantDto getEnseignantById(Long id);

    EnseignantDto updateEnseignant(Long id, EnseignantDto enseignantDto);

    void deleteEnseignant(Long id);
}

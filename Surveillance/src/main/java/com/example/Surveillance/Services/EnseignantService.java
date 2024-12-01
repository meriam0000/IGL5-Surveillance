package com.example.Surveillance.Services;


import com.example.Surveillance.Dtos.EnseignantDto;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface EnseignantService {
    List<EnseignantDto> getAllEnseignants(Authentication authentication);

    EnseignantDto addEnseignant(EnseignantDto enseignantDto);

    EnseignantDto getEnseignantById(Long id);

    EnseignantDto updateEnseignant(Long id, EnseignantDto enseignantDto);

    void deleteEnseignant(Long id);
}

package com.example.Surveillance.Services;

import com.example.Surveillance.Dtos.EtablissementDto;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface EtablissementService {
    List<EtablissementDto> getAllEtablissement(Authentication authentication);

    EtablissementDto addEtablissement(EtablissementDto etablissementDto);

    EtablissementDto getEtablissementById(Long id);


    EtablissementDto updateEtablissement(Long id, EtablissementDto etablissementDto);

    void deleteEtablissement(Long id);
}

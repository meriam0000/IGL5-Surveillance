package com.example.Surveillance.Services;

import com.example.Surveillance.Dtos.EtablissementDto;
import com.example.Surveillance.Util.PageResponse;


public interface EtablissementService {
    PageResponse<EtablissementDto> getAllEtablissement(int page , int size);

    EtablissementDto addEtablissement(EtablissementDto etablissementDto);

    EtablissementDto getEtablissementById(Long id);


    EtablissementDto updateEtablissement(Long id, EtablissementDto etablissementDto);

    void deleteEtablissement(Long id);
}

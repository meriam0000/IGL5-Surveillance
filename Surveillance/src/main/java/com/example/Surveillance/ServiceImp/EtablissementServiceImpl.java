package com.example.Surveillance.ServiceImp;

import com.example.Surveillance.Dtos.EtablissementDto;
import com.example.Surveillance.Entities.Departement;
import com.example.Surveillance.Entities.Etablissement;
import com.example.Surveillance.Repositories.EtablissementRepository;
import com.example.Surveillance.Services.EtablissementService;
import com.example.Surveillance.Util.PageResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class EtablissementServiceImpl implements EtablissementService {
    final EtablissementRepository etablissementRepository;
    final ModelMapper modelMapper;

    @Override
    public PageResponse<EtablissementDto> getAllEtablissement(int page , int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Etablissement> etablissementPage = etablissementRepository.findAll(pageable);

        List<EtablissementDto> etablissementDtoList = etablissementPage.getContent()
                .stream()
                .map(etablissement -> modelMapper.map(etablissement, EtablissementDto.class))
                .toList();

        return new PageResponse<>(
                etablissementDtoList,
                etablissementPage.getNumber(),
                etablissementPage.getSize(),
                etablissementPage.getTotalElements(),
                etablissementPage.getTotalPages(),
                etablissementPage.isFirst(),
                etablissementPage.isLast()
        );

    }

    @Override
    public EtablissementDto addEtablissement(EtablissementDto etablissementDto) {
        return modelMapper.map(etablissementRepository.save(modelMapper.map(etablissementDto, Etablissement.class)), EtablissementDto.class);
    }

    @Override
    public EtablissementDto getEtablissementById(Long id) {
        Etablissement etablissement = etablissementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Etablissement found with ID:"+id));
        return modelMapper.map(etablissement, EtablissementDto.class);
    }

    @Override
    public EtablissementDto updateEtablissement(Long id, EtablissementDto etablissementDto) {
        Etablissement etablissement = etablissementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No etablissement found with ID:"+id));
        etablissement.setId(etablissement.getId());
        etablissement.setNom(etablissement.getNom());
        etablissement.setUniversite(etablissement.getUniversite());
        etablissement.setLocalisation(etablissement.getLocalisation());

        etablissement.setListDepartement(
                etablissementDto.getListDepartement().stream()
                        .map(departementDto-> modelMapper.map(departementDto, Departement.class))
                        .toList()
        );
        return modelMapper.map(etablissementRepository.save(etablissement), EtablissementDto.class);
    }

    @Override
    public void deleteEtablissement(Long id) {
        etablissementRepository.deleteById(id);
    }

}





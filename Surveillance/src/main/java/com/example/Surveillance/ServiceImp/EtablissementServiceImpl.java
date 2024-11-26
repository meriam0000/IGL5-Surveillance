package com.example.Surveillance.ServiceImp;

import com.example.Surveillance.Dtos.DepartementDto;
import com.example.Surveillance.Dtos.EtablissementDto;
import com.example.Surveillance.Entities.Departement;
import com.example.Surveillance.Entities.Enseignant;
import com.example.Surveillance.Entities.Etablissement;
import com.example.Surveillance.Repositories.EtablissementRepository;
import com.example.Surveillance.Services.EtablissementService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class EtablissementServiceImpl implements EtablissementService {

    final EtablissementRepository etablissementRepository;
    final ModelMapper modelMapper;
    @Override
    public List<EtablissementDto> getAllEtablissement() {
        return etablissementRepository.findAll()
                .stream().map(etablissement-> modelMapper.map(etablissement, EtablissementDto.class)).toList();
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

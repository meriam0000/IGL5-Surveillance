package com.example.Surveillance.ServiceImp;

import com.example.Surveillance.Dtos.DepartementDto;
import com.example.Surveillance.Entities.Departement;
import com.example.Surveillance.Entities.Enseignant;
import com.example.Surveillance.Entities.Etablissement;
import com.example.Surveillance.Repositories.DepartementRepository;
import com.example.Surveillance.Services.DepartementService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartementServiceImpl  implements DepartementService {
    final DepartementRepository departeementRepository;
    final ModelMapper modelMapper;

    @Override
    public List<DepartementDto> getAllDepartements() {
        return departeementRepository.findAll()
                .stream().map(departement -> modelMapper.map(departement, DepartementDto.class)).toList();
    }

    @Override
    public DepartementDto addDepartement(DepartementDto departementDto) {
        return modelMapper.map(departeementRepository.save(modelMapper.map(departementDto, Departement.class)), DepartementDto.class);
    }

    @Override
    public DepartementDto getDepartementById(Long id) {
        Departement departement = departeementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Departement found with ID:"+id));
        return modelMapper.map(departement, DepartementDto.class);
    }

    @Override
    public DepartementDto updateDepartement(Long id, DepartementDto departementDto) {

            Departement departement = departeementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Departement found with ID:"+id));
            departement.setNom(departementDto.getNom());
            departement.setId(departement.getId());
            departement.setEnseignants(
                departementDto.getEnseignants().stream()
                        .map(enseignantDto -> modelMapper.map(enseignantDto, Enseignant.class))
                        .toList()
            );
            departement.setSpecialité(departementDto.getSpecialité());
            departement.setEtablissement(modelMapper.map(departementDto.getEtablissement(),Etablissement.class));

        return modelMapper.map(departeementRepository.save(departement), DepartementDto.class);
    }

    @Override
    public void deleteDepartement(Long id) {
        departeementRepository.deleteById(id);
    }
}

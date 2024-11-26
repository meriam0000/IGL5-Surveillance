package com.example.Surveillance.ServiceImp;

import com.example.Surveillance.Dtos.DepartementDto;
import com.example.Surveillance.Entities.Departement;
import com.example.Surveillance.Entities.Enseignant;
import com.example.Surveillance.Entities.Etablissement;
import com.example.Surveillance.Repositories.DepartementRepository;
import com.example.Surveillance.Services.DepartementService;
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
public class DepartementServiceImpl  implements DepartementService {
    final DepartementRepository departeementRepository;
    final ModelMapper modelMapper;

    @Override
    public PageResponse<DepartementDto> getAllDepartements(int page , int size) {
        Pageable pageable = PageRequest.of(page, size);

        // Fetch paginated data from the repository
        Page<Departement> departementPage = departeementRepository.findAll(pageable);

        // Map entities to DTOs
        List<DepartementDto> departementDtoList = departementPage.getContent()
                .stream()
                .map(departement -> modelMapper.map(departement,DepartementDto.class))
                .toList();

        // Create and return a PageResponse object
        return new PageResponse<>(
                departementDtoList,
                departementPage.getNumber(),
                departementPage.getSize(),
                departementPage.getTotalElements(),
                departementPage.getTotalPages(),
                departementPage.isFirst(),
                departementPage.isLast()
        );
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

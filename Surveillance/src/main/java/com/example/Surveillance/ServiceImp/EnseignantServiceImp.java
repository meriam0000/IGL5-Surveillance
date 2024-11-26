package com.example.Surveillance.ServiceImp;

import com.example.Surveillance.Dtos.EnseignantDto;
import com.example.Surveillance.Entities.Departement;
import com.example.Surveillance.Entities.Enseignant;
import com.example.Surveillance.Repositories.EnseignantRepository;
import com.example.Surveillance.Services.EnseignantService;
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
public class EnseignantServiceImp implements EnseignantService {
    final EnseignantRepository enseignantRepository;
    final ModelMapper modelMapper;
    @Override
    public PageResponse<EnseignantDto> getAllEnseignants(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        // Fetch paginated data from the repository
        Page<Enseignant> enseignantsPage = enseignantRepository.findAll(pageable);

        // Map entities to DTOs
        List<EnseignantDto> enseignantDtoList = enseignantsPage.getContent()
                .stream()
                .map(enseignant -> modelMapper.map(enseignant, EnseignantDto.class))
                .toList();

        // Create and return a PageResponse object
        return new PageResponse<>(
                enseignantDtoList,
                enseignantsPage.getNumber(),
                enseignantsPage.getSize(),
                enseignantsPage.getTotalElements(),
                enseignantsPage.getTotalPages(),
                enseignantsPage.isFirst(),
                enseignantsPage.isLast()
        );
    }

    @Override
    public EnseignantDto addEnseignant(EnseignantDto enseignantDto) {
        return modelMapper.map(enseignantRepository.save(modelMapper.map(enseignantDto, Enseignant.class)), EnseignantDto.class);
    }
    @Override
    public EnseignantDto getEnseignantById(Long id) {
        Enseignant enseignant = enseignantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Enseignant found with ID:"+id));
        return modelMapper.map(enseignant, EnseignantDto.class);
    }
    @Override
    public EnseignantDto updateEnseignant(Long id, EnseignantDto enseignantDto) {
        Enseignant enseignant = enseignantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Enseignant found with ID:"+id));
        enseignant.setNom(enseignantDto.getNom());
        enseignant.setPrenom(enseignantDto.getPrenom());
        enseignant.setCin(enseignantDto.getCin());
        enseignant.setDepartement(modelMapper.map(enseignantDto.getDepartement(), Departement.class));
        enseignant.setGrade(enseignantDto.getGrade());
        enseignant.setEmail(enseignantDto.getEmail());
        enseignant.setNumeroTelephone(enseignantDto.getNumeroTelephone());
        enseignant.setNbHeureSurveillanceMaximale(enseignantDto.getNbHeureSurveillanceMaximale());
        return modelMapper.map(enseignantRepository.save(enseignant), EnseignantDto.class);
    }
    @Override
    public void deleteEnseignant(Long id) {
        enseignantRepository.deleteById(id);
    }


}

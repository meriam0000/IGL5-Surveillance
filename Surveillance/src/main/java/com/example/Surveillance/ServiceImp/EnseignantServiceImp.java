package com.example.Surveillance.ServiceImp;

import com.example.Surveillance.Dtos.EnseignantDto;
import com.example.Surveillance.Entities.Departement;
import com.example.Surveillance.Entities.Enseignant;
import com.example.Surveillance.Repositories.EnseignantRepository;
import com.example.Surveillance.Services.EnseignantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnseignantServiceImp implements EnseignantService {
    final EnseignantRepository enseignantRepository;
    final ModelMapper modelMapper;
    @Override
    public List<EnseignantDto> getAllEnseignants() {
        return enseignantRepository.findAll()
                .stream().map(enseignant -> modelMapper.map(enseignant, EnseignantDto.class)).toList();
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

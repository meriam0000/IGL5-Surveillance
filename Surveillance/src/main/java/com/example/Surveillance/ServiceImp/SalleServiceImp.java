package com.example.Surveillance.ServiceImp;

import com.example.Surveillance.Dtos.SalleDto;
import com.example.Surveillance.Entities.Departement;
import com.example.Surveillance.Entities.Salle;
import com.example.Surveillance.Repositories.SalleRepository;
import com.example.Surveillance.Services.SalleService;
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
public class SalleServiceImp implements SalleService {
    final SalleRepository salleRepository;
    final ModelMapper modelMapper;
    @Override
    public PageResponse<SalleDto> getAllSalles(int page , int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Salle> sallePage = salleRepository.findAll(pageable);

        List<SalleDto> salleDtoList = sallePage.getContent()
                .stream()
                .map(salle -> modelMapper.map(salle, SalleDto.class))
                .toList();

        return new PageResponse<>(
                salleDtoList,
                sallePage.getNumber(),
                sallePage.getSize(),
                sallePage.getTotalElements(),
                sallePage.getTotalPages(),
                sallePage.isFirst(),
                sallePage.isLast()
        );
    }
    @Override
    public SalleDto addSalle(SalleDto salleDto) {
        return modelMapper.map(salleRepository.save(modelMapper.map(salleDto, Salle.class)), SalleDto.class);
    }
    @Override
    public SalleDto getSalleById(Long id) {
        Salle salle = salleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Salle found with ID:"+id));
        return modelMapper.map(salle, SalleDto.class);
    }
    @Override
    public SalleDto updateSalle(Long id, SalleDto salleDto) {
        Salle salle = salleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Salle found with ID:"+id));
        salle.setNom(salleDto.getNom());
        salle.setType(salleDto.getType());
        salle.setCapacite(salleDto.getCapacite());
        salle.setDepartement(modelMapper.map(salleDto.getDepartement(), Departement.class));
        return modelMapper.map(salleRepository.save(salle), SalleDto.class);
    }
    @Override
    public void deleteSalle(Long id) {
        salleRepository.deleteById(id);
    }
}

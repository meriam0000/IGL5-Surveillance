package com.example.Surveillance.ServiceImp;

import com.example.Surveillance.Dtos.AdminDepartementDto;
import com.example.Surveillance.Dtos.AdminEtablissementDto;
import com.example.Surveillance.Entities.AdminEtablissement;
import com.example.Surveillance.Exception.ResourceNotFoundException;
import com.example.Surveillance.Repositories.AdminEtablissementRepository;
import com.example.Surveillance.Services.AdminEtablissementService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminEtablissementServiceImpl implements AdminEtablissementService {


    private final AdminEtablissementRepository adminEtablissementRepository;
    private final ModelMapper modelMapper;

    @Override
    public AdminEtablissementDto saveAdminEtablissement(AdminEtablissementDto adminEtablissement) {
        AdminEtablissement adminEtablissement1= modelMapper.map(adminEtablissement, AdminEtablissement.class);
        return modelMapper.map(adminEtablissementRepository.save(adminEtablissement1),AdminEtablissementDto.class);
    }

    @Override
    public List<AdminEtablissementDto> findAllAdminEtablissements() {
        return adminEtablissementRepository.findAll()
                .stream()
                .map(adminetab -> modelMapper.map(adminetab, AdminEtablissementDto.class))
                .toList();
    }

    @Override
    public AdminEtablissementDto findAdminEtablissementById(Long id) {
        return modelMapper.map(adminEtablissementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AdminEtablissementDto with id " + id + " not found")), AdminEtablissementDto.class);
    }

    @Override
    public AdminEtablissementDto updateAdminEtablissement(Long id, AdminEtablissementDto adminEtablissement) {
        AdminEtablissementDto existing = findAdminEtablissementById(id);
        existing.setFullName(adminEtablissement.getFullName());
        existing.setEmail(adminEtablissement.getEmail());
        existing.setEtablissement(adminEtablissement.getEtablissement());
        existing.setFirstname(adminEtablissement.getFirstname());
        existing.setPassword(existing.getPassword());
        existing.setRole(adminEtablissement.getRole());

        AdminEtablissement existing1 = modelMapper.map(existing, AdminEtablissement.class);

        return modelMapper.map(adminEtablissementRepository.save(existing1),AdminEtablissementDto.class);
    }

    @Override
    public void deleteAdminEtablissement(Long id) {
        adminEtablissementRepository.deleteById(id);
    }


}

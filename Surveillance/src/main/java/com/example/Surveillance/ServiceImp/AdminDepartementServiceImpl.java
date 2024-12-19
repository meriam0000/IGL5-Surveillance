package com.example.Surveillance.ServiceImp;


import com.example.Surveillance.Dtos.AdminDepartementDto;
import com.example.Surveillance.Entities.AdminDepartement;
import com.example.Surveillance.Exception.ResourceNotFoundException;
import com.example.Surveillance.Repositories.AdminDepartementRepository;
import com.example.Surveillance.Services.AdminDepartementService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Surveillance.Entities.user.Role.ADMIN_DEPARTEMENT;

@Service
@AllArgsConstructor
public class AdminDepartementServiceImpl implements AdminDepartementService {


    private final AdminDepartementRepository adminDepartementRepository;
    private final ModelMapper modelMapper;

    @Override
    public AdminDepartementDto saveAdminDepartement(AdminDepartementDto adminDepartement) {
        adminDepartement.setRole(ADMIN_DEPARTEMENT);
        AdminDepartement adminDepartement1 = modelMapper.map(adminDepartement, AdminDepartement.class);
        return modelMapper.map(adminDepartementRepository.save(adminDepartement1),AdminDepartementDto.class);
    }

    @Override
    public List<AdminDepartementDto> findAllAdminDepartements() {
        return adminDepartementRepository.findAll()
                .stream()
                .map(adminDepartement -> modelMapper.map(adminDepartement, AdminDepartementDto.class))
                .toList();
    }


    @Override
    public AdminDepartementDto findAdminDepartementById(Long id) {
        return modelMapper.map(adminDepartementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AdminDepartement with id " + id + " not found")), AdminDepartementDto.class);
    }

    @Override
    public AdminDepartementDto updateAdminDepartement(Long id, AdminDepartementDto adminDepartement) {
        AdminDepartementDto existing = findAdminDepartementById(id);
        existing.setFullName(adminDepartement.getFullName());
        existing.setEmail(adminDepartement.getEmail());
        existing.setDepartement(adminDepartement.getDepartement());
        existing.setFirstname(adminDepartement.getFirstname());
        existing.setRole(adminDepartement.getRole());
        existing.setPassword(adminDepartement.getPassword());

        AdminDepartement existingEntity = modelMapper.map(existing, AdminDepartement.class);

        return modelMapper.map(adminDepartementRepository.save(existingEntity),AdminDepartementDto.class);
    }

    @Override
    public void deleteAdminDepartement(Long id) {
        adminDepartementRepository.deleteById(id);
    }


}


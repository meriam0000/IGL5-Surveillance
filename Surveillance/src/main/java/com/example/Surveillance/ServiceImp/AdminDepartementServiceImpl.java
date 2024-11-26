package com.example.Surveillance.ServiceImp;


import com.example.Surveillance.Entities.AdminDepartement;
import com.example.Surveillance.Exception.ResourceNotFoundException;
import com.example.Surveillance.Repositories.AdminDepartementRepository;
import com.example.Surveillance.Services.AdminDepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDepartementServiceImpl implements AdminDepartementService {

    @Autowired
    private AdminDepartementRepository adminDepartementRepository;

    @Override
    public AdminDepartement saveAdminDepartement(AdminDepartement adminDepartement) {
        return adminDepartementRepository.save(adminDepartement);
    }

    @Override
    public List<AdminDepartement> findAllAdminDepartements() {
        return adminDepartementRepository.findAll();
    }

    @Override
    public AdminDepartement findAdminDepartementById(Long id) {
        return adminDepartementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AdminDepartement with id " + id + " not found"));
    }

    @Override
    public AdminDepartement updateAdminDepartement(Long id, AdminDepartement adminDepartement) {
        AdminDepartement existing = findAdminDepartementById(id);
        existing.setFullName(adminDepartement.getFullName());
        existing.setEmail(adminDepartement.getEmail());
        // Update other fields as needed
        return adminDepartementRepository.save(existing);
    }

    @Override
    public void deleteAdminDepartement(Long id) {
        adminDepartementRepository.deleteById(id);
    }

    @Override
    public AdminDepartement getAdminDepartementByUsername(String username) {
        return adminDepartementRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("AdminDepartement with username " + username + " not found"));
    }
}


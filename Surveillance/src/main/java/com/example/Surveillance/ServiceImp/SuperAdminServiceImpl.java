package com.example.Surveillance.ServiceImp;


import com.example.Surveillance.Entities.SuperAdmin;
import com.example.Surveillance.Exception.ResourceNotFoundException;
import com.example.Surveillance.Repositories.SuperAdminRepository;
import com.example.Surveillance.Services.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {

    @Autowired
    private SuperAdminRepository superAdminRepository;

    @Override
    public SuperAdmin saveSuperAdmin(SuperAdmin superAdmin) {
        return superAdminRepository.save(superAdmin);
    }

    @Override
    public List<SuperAdmin> findAllSuperAdmins() {
        return superAdminRepository.findAll();
    }

    @Override
    public SuperAdmin findSuperAdminById(Long id) {
        return superAdminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SuperAdmin with id " + id + " not found"));
    }

    @Override
    public SuperAdmin updateSuperAdmin(Long id, SuperAdmin superAdmin) {
        SuperAdmin existing = findSuperAdminById(id);
        existing.setFullName(superAdmin.getFullName());
        existing.setEmail(superAdmin.getEmail());
        // Update other fields as needed
        return superAdminRepository.save(existing);
    }

    @Override
    public void deleteSuperAdmin(Long id) {
        superAdminRepository.deleteById(id);
    }

    @Override
    public SuperAdmin getSuperAdminByUsername(String username) {
        return superAdminRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("SuperAdmin with username " + username + " not found"));
    }
}


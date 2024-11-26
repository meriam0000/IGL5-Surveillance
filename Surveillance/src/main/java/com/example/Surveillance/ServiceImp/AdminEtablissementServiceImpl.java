package com.example.Surveillance.ServiceImp;

import com.example.Surveillance.Entities.AdminEtablissement;
import com.example.Surveillance.Exception.ResourceNotFoundException;
import com.example.Surveillance.Repositories.AdminEtablissementRepository;
import com.example.Surveillance.Services.AdminEtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminEtablissementServiceImpl implements AdminEtablissementService {

    @Autowired
    private AdminEtablissementRepository adminEtablissementRepository;

    @Override
    public AdminEtablissement saveAdminEtablissement(AdminEtablissement adminEtablissement) {
        return adminEtablissementRepository.save(adminEtablissement);
    }

    @Override
    public List<AdminEtablissement> findAllAdminEtablissements() {
        return adminEtablissementRepository.findAll();
    }

    @Override
    public AdminEtablissement findAdminEtablissementById(Long id) {
        return adminEtablissementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AdminEtablissement with id " + id + " not found"));
    }

    @Override
    public AdminEtablissement updateAdminEtablissement(Long id, AdminEtablissement adminEtablissement) {
        AdminEtablissement existing = findAdminEtablissementById(id);
        existing.setFullName(adminEtablissement.getFullName());
        existing.setEmail(adminEtablissement.getEmail());
        // Update other fields as needed
        return adminEtablissementRepository.save(existing);
    }

    @Override
    public void deleteAdminEtablissement(Long id) {
        adminEtablissementRepository.deleteById(id);
    }

    @Override
    public AdminEtablissement getAdminEtablissementByUsername(String username) {
        return adminEtablissementRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("AdminEtablissement with username " + username + " not found"));
    }
}

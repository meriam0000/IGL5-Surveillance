package com.example.Surveillance.Services;



import com.example.Surveillance.Entities.AdminDepartement;

import java.util.List;

public interface AdminDepartementService {

    AdminDepartement saveAdminDepartement(AdminDepartement adminDepartement);

    List<AdminDepartement> findAllAdminDepartements();

    AdminDepartement findAdminDepartementById(Long id);

    AdminDepartement updateAdminDepartement(Long id, AdminDepartement adminDepartement);

    void deleteAdminDepartement(Long id);


}

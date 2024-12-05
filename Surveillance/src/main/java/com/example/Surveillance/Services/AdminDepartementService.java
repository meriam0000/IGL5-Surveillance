package com.example.Surveillance.Services;



import com.example.Surveillance.Dtos.AdminDepartementDto;

import java.util.List;

public interface AdminDepartementService {

    AdminDepartementDto saveAdminDepartement(AdminDepartementDto adminDepartement);

    List<AdminDepartementDto> findAllAdminDepartements();

    AdminDepartementDto findAdminDepartementById(Long id);

    AdminDepartementDto updateAdminDepartement(Long id, AdminDepartementDto adminDepartement);

    void deleteAdminDepartement(Long id);


}

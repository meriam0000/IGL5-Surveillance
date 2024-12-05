package com.example.Surveillance.Services;



import com.example.Surveillance.Dtos.SuperAdminDto;

import java.util.List;

public interface SuperAdminService {

    SuperAdminDto saveSuperAdmin(SuperAdminDto superAdmin);

    List<SuperAdminDto> findAllSuperAdmins();

    SuperAdminDto findSuperAdminById(Long id);

    SuperAdminDto updateSuperAdmin(Long id, SuperAdminDto superAdmin);

    void deleteSuperAdmin(Long id);


}


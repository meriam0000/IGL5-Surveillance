package com.example.Surveillance.Services;



import com.example.Surveillance.Entities.SuperAdmin;

import java.util.List;

public interface SuperAdminService {

    SuperAdmin saveSuperAdmin(SuperAdmin superAdmin);

    List<SuperAdmin> findAllSuperAdmins();

    SuperAdmin findSuperAdminById(Long id);

    SuperAdmin updateSuperAdmin(Long id, SuperAdmin superAdmin);

    void deleteSuperAdmin(Long id);


}


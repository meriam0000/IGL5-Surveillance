package com.example.Surveillance.Services;


import com.example.Surveillance.Entities.AdminEtablissement;

import java.util.List;

public interface AdminEtablissementService {

    AdminEtablissement saveAdminEtablissement(AdminEtablissement adminEtablissement);

    List<AdminEtablissement> findAllAdminEtablissements();

    AdminEtablissement findAdminEtablissementById(Long id);

    AdminEtablissement updateAdminEtablissement(Long id, AdminEtablissement adminEtablissement);

    void deleteAdminEtablissement(Long id);


}


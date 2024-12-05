package com.example.Surveillance.Services;


import com.example.Surveillance.Dtos.AdminEtablissementDto;

import java.util.List;

public interface AdminEtablissementService {

    AdminEtablissementDto saveAdminEtablissement(AdminEtablissementDto adminEtablissement);

    List<AdminEtablissementDto> findAllAdminEtablissements();

    AdminEtablissementDto findAdminEtablissementById(Long id);

    AdminEtablissementDto updateAdminEtablissement(Long id, AdminEtablissementDto adminEtablissement);

    void deleteAdminEtablissement(Long id);


}


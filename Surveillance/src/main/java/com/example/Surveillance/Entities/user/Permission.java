package com.example.Surveillance.Entities.user;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    // SuperAdmin permissions
    SUPERADMIN_READ("superadmin:read"),
    SUPERADMIN_UPDATE("superadmin:update"),
    SUPERADMIN_CREATE("superadmin:create"),
    SUPERADMIN_DELETE("superadmin:delete"),

    // AdminEtablissement permissions
    ADMIN_ETABLISSEMENT_READ("admin_etablissement:read"),
    ADMIN_ETABLISSEMENT_UPDATE("admin_etablissement:update"),
    ADMIN_ETABLISSEMENT_CREATE("admin_etablissement:create"),
    ADMIN_ETABLISSEMENT_DELETE("admin_etablissement:delete"),

    // AdminDepartement permissions
    ADMIN_DEPARTEMENT_READ("admin_departement:read"),
    ADMIN_DEPARTEMENT_UPDATE("admin_departement:update"),
    ADMIN_DEPARTEMENT_CREATE("admin_departement:create"),
    ADMIN_DEPARTEMENT_DELETE("admin_departement:delete");

    @Getter
    private final String permission;
}

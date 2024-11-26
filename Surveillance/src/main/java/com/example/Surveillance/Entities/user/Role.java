package com.example.Surveillance.Entities.user;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.Surveillance.Entities.user.Permission.*;


@RequiredArgsConstructor
public enum Role {

    // Role for SuperAdmin
    SUPERADMIN(
            Set.of(
                    SUPERADMIN_READ,
                    SUPERADMIN_UPDATE,
                    SUPERADMIN_CREATE,
                    SUPERADMIN_DELETE,
                    ADMIN_ETABLISSEMENT_READ,
                    ADMIN_ETABLISSEMENT_UPDATE,
                    ADMIN_ETABLISSEMENT_CREATE,
                    ADMIN_ETABLISSEMENT_DELETE,
                    ADMIN_DEPARTEMENT_READ,
                    ADMIN_DEPARTEMENT_UPDATE,
                    ADMIN_DEPARTEMENT_CREATE,
                    ADMIN_DEPARTEMENT_DELETE
            )
    ),

    // Role for AdminEtablissement
    ADMIN_ETABLISSEMENT(
            Set.of(
                    ADMIN_ETABLISSEMENT_READ,
                    ADMIN_ETABLISSEMENT_UPDATE,
                    ADMIN_ETABLISSEMENT_CREATE,
                    ADMIN_ETABLISSEMENT_DELETE,
                    ADMIN_DEPARTEMENT_READ,
                    ADMIN_DEPARTEMENT_UPDATE,
                    ADMIN_DEPARTEMENT_CREATE,
                    ADMIN_DEPARTEMENT_DELETE
            )
    ),

    // Role for AdminDepartement
    ADMIN_DEPARTEMENT(
            Set.of(
                    ADMIN_DEPARTEMENT_READ,
                    ADMIN_DEPARTEMENT_UPDATE,
                    ADMIN_DEPARTEMENT_CREATE,
                    ADMIN_DEPARTEMENT_DELETE
            )
    ),

    // Role for a standard user with no specific permissions
    USER(Collections.emptySet());

    @Getter
    private final Set<Permission> permissions;

    // Convert permissions to Spring Security authorities
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}

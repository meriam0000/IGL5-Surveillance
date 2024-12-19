package com.example.Surveillance.ServiceImp;

import com.example.Surveillance.Dtos.DepartementDto;
import com.example.Surveillance.Entities.*;
import com.example.Surveillance.Entities.user.Permission;
import com.example.Surveillance.Entities.user.User;
import com.example.Surveillance.Exception.ForbiddenException;
import com.example.Surveillance.Exception.ResourceNotFoundException;
import com.example.Surveillance.Repositories.DepartementRepository;
import com.example.Surveillance.Services.DepartementService;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartementServiceImpl  implements DepartementService {
    final DepartementRepository departeementRepository;

    final ModelMapper modelMapper;
    @Override
    public List<DepartementDto> getAllDepartements( Authentication authentication){

        User user = ((User) authentication.getPrincipal());
        if( user.getRole().getPermissions().contains(Permission.SUPERADMIN_READ)){
            return departeementRepository.findAll()
                    .stream()
                    .map(departement -> modelMapper.map(departement,DepartementDto.class))
                    .toList();
        } else if (user.getRole().getPermissions().contains(Permission.ADMIN_DEPARTEMENT_READ)){
            AdminDepartement adminDepartement = (AdminDepartement) user;
            return List.of(modelMapper
                    .map(adminDepartement.getDepartement(),DepartementDto.class));


        } else if(user.getRole().getPermissions().contains(Permission.ADMIN_ETABLISSEMENT_READ)){
            AdminEtablissement adminEtablissement = (AdminEtablissement) user;
            List<Departement> departementList=adminEtablissement.getEtablissement().getListDepartement();
            return
                    departementList
                    .stream()
                    .map(departement -> modelMapper.map(departement,DepartementDto.class))
                    .toList();
        }
        throw new ForbiddenException("User does not have permission to view departments");

    }



    @Override
    public DepartementDto addDepartement(DepartementDto departementDto) {

        return modelMapper.map(departeementRepository.save(modelMapper.map(departementDto, Departement.class)), DepartementDto.class);
    }

    @Override
    public DepartementDto getDepartementById(Long id) {
        Departement departement = departeementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Departement found with ID:"+id));
        return modelMapper.map(departement, DepartementDto.class);
    }

    @Override
    public DepartementDto updateDepartement(Long id, DepartementDto departementDto, Authentication authentication) {
        // Get the currently authenticated user
        User user = (User) authentication.getPrincipal();

        // Check if the user has SUPERADMIN role (unrestricted access)
        if (user.getRole().getPermissions().contains(Permission.SUPERADMIN_UPDATE)) {
            // SUPERADMIN can update any department, no restrictions
        }
        // Check if the user has ADMIN_ETABLISSEMENT role
        else if (user.getRole().getPermissions().contains(Permission.ADMIN_ETABLISSEMENT_UPDATE)) {
            AdminEtablissement adminEtablissement = (AdminEtablissement) user;

            // Ensure that the department being updated belongs to the same establishment
            if (!adminEtablissement.getEtablissement().getId().equals(departementDto.getEtablissement().getId())) {
                throw new ForbiddenException("You are not authorized to update departments outside your establishment.");
            }
        }
        // Check if the user has ADMIN_DEPARTEMENT role
        else if (user.getRole().getPermissions().contains(Permission.ADMIN_DEPARTEMENT_UPDATE)) {
            AdminDepartement adminDepartement = (AdminDepartement) user;

            // Ensure that the user is attempting to update the department they are assigned to
            if (!adminDepartement.getDepartement().getId().equals(id)) {
                throw new ForbiddenException("You are not authorized to update this department.");
            }
        }
        else {
            // If the user doesn't have permission, throw an exception
            throw new ForbiddenException("You do not have permission to update departments.");
        }

        // Proceed with the department update if all checks pass
        Departement departement = departeementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Departement found with ID:" + id));

        // Map the updated values from departementDto to the departement entity
        departement.setNom(departementDto.getNom());
        departement.setSpecialité(departementDto.getSpecialité());
        departement.setEnseignants(
                departementDto.getEnseignants().stream()
                        .map(enseignantDto -> modelMapper.map(enseignantDto, Enseignant.class))
                        .toList()
        );
        departement.setEtablissement(modelMapper.map(departementDto.getEtablissement(), Etablissement.class));

        // Save the updated department and return the updated DTO
        return modelMapper.map(departeementRepository.save(departement), DepartementDto.class);
}



    @Override
    public void deleteDepartement(Long id) {
        departeementRepository.deleteById(id);
    }

    @Override
    public List<DepartementDto> getDepartementsByEtablissementId(Long id) {
        return departeementRepository.findAllByEtablissementId(id)
                .stream()
                .map(departement -> modelMapper.map(departement, DepartementDto.class))
                .toList();
    }
}

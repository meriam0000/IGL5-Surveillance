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
            return departeementRepository.findByAdminDepartements(List.of((AdminDepartement) user))
                    .stream()
                    .map(departement -> modelMapper.map(departement,DepartementDto.class))
                    .toList();
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
    public DepartementDto updateDepartement(Long id, DepartementDto departementDto) {

        Departement departement = departeementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Departement found with ID:"+id));
        departement.setNom(departementDto.getNom());
        departement.setId(departement.getId());
        departement.setEnseignants(
                departementDto.getEnseignants().stream()
                        .map(enseignantDto -> modelMapper.map(enseignantDto, Enseignant.class))
                        .toList()
        );
        departement.setSpecialité(departementDto.getSpecialité());
        departement.setEtablissement(modelMapper.map(departementDto.getEtablissement(),Etablissement.class));

        return modelMapper.map(departeementRepository.save(departement), DepartementDto.class);
    }

    @Override
    public void deleteDepartement(Long id) {
        departeementRepository.deleteById(id);
    }
}

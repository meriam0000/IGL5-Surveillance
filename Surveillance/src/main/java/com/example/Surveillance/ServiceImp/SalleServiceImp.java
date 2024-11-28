package com.example.Surveillance.ServiceImp;

import com.example.Surveillance.Dtos.SalleDto;
import com.example.Surveillance.Entities.AdminDepartement;
import com.example.Surveillance.Entities.AdminEtablissement;
import com.example.Surveillance.Entities.Departement;
import com.example.Surveillance.Entities.Salle;
import com.example.Surveillance.Entities.user.Permission;
import com.example.Surveillance.Entities.user.User;
import com.example.Surveillance.Exception.ForbiddenException;
import com.example.Surveillance.Repositories.SalleRepository;
import com.example.Surveillance.Services.SalleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class SalleServiceImp implements SalleService {
    final SalleRepository salleRepository;
    final ModelMapper modelMapper;
    @Override
    public List<SalleDto> getAllSalles(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        if(user.getRole().getPermissions().contains(Permission.SUPERADMIN_READ)){
        return salleRepository.findAll()
                .stream()
                .map(salle -> modelMapper.map(salle, SalleDto.class))
                .toList();}
        else if(user.getRole().getPermissions().contains(Permission.ADMIN_ETABLISSEMENT_READ)){
            AdminEtablissement adminEtablissement = (AdminEtablissement) user;
            return adminEtablissement.getEtablissement().getListDepartement().stream().flatMap(departement -> departement.getSalles().stream()).toList().stream()
                    .map(salle -> modelMapper.map(salle, SalleDto.class)).toList();
        }

        else if(user.getRole().getPermissions().contains(Permission.ADMIN_DEPARTEMENT_READ)){
            AdminDepartement adminDepartement = (AdminDepartement) user;
            return adminDepartement.getDepartement().getSalles().stream().map(salle -> modelMapper.map(salle, SalleDto.class)).toList();

        }
        else{
            throw new ForbiddenException("User does not have permission to view Salles");

        }

    }
    @Override
    public SalleDto addSalle(SalleDto salleDto) {
        return modelMapper.map(salleRepository.save(modelMapper.map(salleDto, Salle.class)), SalleDto.class);
    }
    @Override
    public SalleDto getSalleById(Long id) {
        Salle salle = salleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Salle found with ID:"+id));
        return modelMapper.map(salle, SalleDto.class);
    }
    @Override
    public SalleDto updateSalle(Long id, SalleDto salleDto) {
        Salle salle = salleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Salle found with ID:"+id));
        salle.setNom(salleDto.getNom());
        salle.setType(salleDto.getType());
        salle.setCapacite(salleDto.getCapacite());
        salle.setDepartement(modelMapper.map(salleDto.getDepartement(), Departement.class));
        return modelMapper.map(salleRepository.save(salle), SalleDto.class);
    }
    @Override
    public void deleteSalle(Long id) {
        salleRepository.deleteById(id);
    }
}

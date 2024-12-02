package com.example.Surveillance.ServiceImp;

import com.example.Surveillance.Dtos.SectionDto;
import com.example.Surveillance.Entities.AdminDepartement;
import com.example.Surveillance.Entities.AdminEtablissement;
import com.example.Surveillance.Entities.Section;
import com.example.Surveillance.Entities.user.Permission;
import com.example.Surveillance.Entities.user.User;
import com.example.Surveillance.Exception.ForbiddenException;
import com.example.Surveillance.Repositories.SectionRepository;
import com.example.Surveillance.Services.SectionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SectionServiceImp implements SectionService {
    private final SectionRepository sectionRepository;
    private final ModelMapper modelMapper;

    @Override
    public SectionDto addSection(SectionDto sectionDto) {
        // Map DTO to entity, save, and map back to DTO
        Section section = modelMapper.map(sectionDto, Section.class);
        return modelMapper.map(sectionRepository.save(section), SectionDto.class);
    }

    @Override
    public SectionDto getSectionById(Long id) {
        // Fetch section by ID or throw an exception
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Section found with ID: " + id));
        return modelMapper.map(section, SectionDto.class);
    }

    @Override
    public SectionDto updateSection(Long id, SectionDto sectionDto) {
        // Fetch section by ID or throw an exception
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Section found with ID: " + id));

        // Update the entity fields
        section.setNom(sectionDto.getNom());
        section.setSpecialite(sectionDto.getSpecialite());
        section.setDepartement(modelMapper.map(sectionDto.getDepartement(), Section.class).getDepartement());

        // Save the updated entity and map to DTO
        return modelMapper.map(sectionRepository.save(section), SectionDto.class);
    }

    @Override
    public List<SectionDto> getAllSections(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (user.getRole().getPermissions().contains(Permission.SUPERADMIN_READ)) {
            // Super Admin: Access all sections
            return sectionRepository.findAll()
                    .stream()
                    .map(section -> modelMapper.map(section, SectionDto.class))
                    .toList();
        } else if (user.getRole().getPermissions().contains(Permission.ADMIN_ETABLISSEMENT_READ)) {
            // Admin Etablissement: Access sections within their establishment
            AdminEtablissement adminEtablissement = (AdminEtablissement) user;
            return adminEtablissement.getEtablissement().getListDepartement()
                    .stream()
                    .flatMap(departement -> departement.getSections().stream())
                    .map(section -> modelMapper.map(section, SectionDto.class))
                    .toList();
        } else if (user.getRole().getPermissions().contains(Permission.ADMIN_DEPARTEMENT_READ)) {
            // Admin Departement: Access sections within their department
            AdminDepartement adminDepartement = (AdminDepartement) user;
            return adminDepartement.getDepartement().getSections()
                    .stream()
                    .map(section -> modelMapper.map(section, SectionDto.class))
                    .toList();
        } else {
            // Unauthorized access
            throw new ForbiddenException("User does not have permission to view Sections");
        }
    }

    @Override
    public void deleteSalle(Long id) {
        // Check if section exists before deleting
        if (!sectionRepository.existsById(id)) {
            throw new EntityNotFoundException("No Section found with ID: " + id);
        }
        sectionRepository.deleteById(id);
    }
}

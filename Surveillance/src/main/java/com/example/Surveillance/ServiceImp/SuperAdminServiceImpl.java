package com.example.Surveillance.ServiceImp;
import com.example.Surveillance.Dtos.SuperAdminDto;
import com.example.Surveillance.Entities.SuperAdmin;
import com.example.Surveillance.Exception.ResourceNotFoundException;
import com.example.Surveillance.Repositories.SuperAdminRepository;
import com.example.Surveillance.Services.SuperAdminService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class SuperAdminServiceImpl implements SuperAdminService {


    private final SuperAdminRepository superAdminRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SuperAdminDto saveSuperAdmin(SuperAdminDto superAdmin) {
        SuperAdmin superAdmin1= modelMapper.map(superAdmin, SuperAdmin.class);
        return modelMapper.map(superAdminRepository.save(superAdmin1),SuperAdminDto.class);
    }

    @Override
    public List<SuperAdminDto> findAllSuperAdmins() {
        return superAdminRepository.findAll()
                .stream()
                .map(adminetab -> modelMapper.map(adminetab, SuperAdminDto.class))
                .toList();
    }

    @Override
    public SuperAdminDto findSuperAdminById(Long id) {
        return modelMapper.map(superAdminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SuperAdminDto with id " + id + " not found")), SuperAdminDto.class);
    }

    @Override
    public SuperAdminDto updateSuperAdmin(Long id, SuperAdminDto superAdmin) {
        SuperAdminDto existing = findSuperAdminById(id);
        existing.setFullName(superAdmin.getFullName());
        existing.setEmail(superAdmin.getEmail());
        existing.setFirstname(superAdmin.getFirstname());
        existing.setPassword(superAdmin.getPassword());
        existing.setRole(superAdmin.getRole());

        SuperAdmin existing1 = modelMapper.map(existing, SuperAdmin.class);
        return modelMapper.map(superAdminRepository.save(existing1), SuperAdminDto.class);
    }

    @Override
    public void deleteSuperAdmin(Long id) {
        superAdminRepository.deleteById(id);
    }


}


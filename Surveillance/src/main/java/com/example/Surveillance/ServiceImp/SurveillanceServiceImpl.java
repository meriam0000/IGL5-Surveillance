package com.example.Surveillance.ServiceImp;

import com.example.Surveillance.Dtos.SurveillanceDTO;
import com.example.Surveillance.Entities.Surveillance;
import com.example.Surveillance.Entities.Salle;
import com.example.Surveillance.Entities.Session;
import com.example.Surveillance.Repositories.SurveillanceRepository;
import com.example.Surveillance.Repositories.SalleRepository;
import com.example.Surveillance.Repositories.SessionRepository;
import com.example.Surveillance.Services.SurveillanceService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SurveillanceServiceImpl implements SurveillanceService {

    private final SurveillanceRepository surveillanceRepository;
    private final SessionRepository sessionRepository;
    private final SalleRepository salleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<SurveillanceDTO> getAllSurveillances() {
        return surveillanceRepository.findAll()
                .stream()
                .map(surveillance -> modelMapper.map(surveillance, SurveillanceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SurveillanceDTO getSurveillanceById(Long id) {
        Surveillance surveillance = surveillanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Surveillance not found with ID " + id));
        return modelMapper.map(surveillance, SurveillanceDTO.class);
    }

    @Override
    public SurveillanceDTO addSurveillance(SurveillanceDTO surveillanceDTO) {
        // Fetch Session - ensure no recursion here
        Session session = sessionRepository.findById(surveillanceDTO.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        // Fetch Salle (classroom) - ensure no recursion here
        Salle salle = salleRepository.findById(surveillanceDTO.getSalleId())
                .orElseThrow(() -> new RuntimeException("Salle not found"));

        // Convert DTO to Entity and associate session and salle
        Surveillance surveillance = modelMapper.map(surveillanceDTO, Surveillance.class);

        // Set the session and salle on the surveillance entity, without triggering recursion
        surveillance.setSession(session);
        surveillance.setSalle(salle);

        // Save the surveillance
        // Ensure that when saving, you don't get stuck in infinite recursion due to logging/printing
        surveillance = surveillanceRepository.save(surveillance);

        // Return the saved surveillance as DTO
        return modelMapper.map(surveillance, SurveillanceDTO.class);
    }

    @Override
    public SurveillanceDTO updateSurveillance(Long id, SurveillanceDTO surveillanceDTO) {
        Surveillance surveillance = surveillanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Surveillance not found with ID " + id));

        // Fetch Session
        Session session = sessionRepository.findById(surveillanceDTO.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        // Fetch Salle (classroom)
        Salle salle = salleRepository.findById(surveillanceDTO.getSalleId())
                .orElseThrow(() -> new RuntimeException("Salle not found"));

        // Update surveillance entity
        surveillance.setNom(surveillanceDTO.getNom());
        surveillance.setJour(surveillanceDTO.getJour());
        surveillance.setDateDebut(surveillanceDTO.getDateDebut());
        surveillance.setDateFin(surveillanceDTO.getDateFin());
        surveillance.setSession(session);
        surveillance.setSalle(salle);

        // Save updated surveillance
        return modelMapper.map(surveillanceRepository.save(surveillance), SurveillanceDTO.class);
    }

    @Override
    public void deleteSurveillance(Long id) {
        surveillanceRepository.deleteById(id);
    }
}

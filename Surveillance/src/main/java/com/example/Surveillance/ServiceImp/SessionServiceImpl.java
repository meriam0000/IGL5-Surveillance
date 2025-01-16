package com.example.Surveillance.ServiceImp;

import com.example.Surveillance.Dtos.SessionDTO;
import com.example.Surveillance.Entities.Session;
import com.example.Surveillance.Exception.ForbiddenException;
import com.example.Surveillance.Repositories.SessionRepository;
import com.example.Surveillance.Services.SessionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<SessionDTO> getAllSessions() {
        return sessionRepository.findAll()
                .stream()
                .map(session -> modelMapper.map(session, SessionDTO.class))
                .toList();
    }

    @Override
    public SessionDTO getSessionById(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Session found with ID: " + id));
        return modelMapper.map(session, SessionDTO.class);
    }

    @Override
    public SessionDTO addSession(SessionDTO sessionDTO) {
        Session session = modelMapper.map(sessionDTO, Session.class);
        return modelMapper.map(sessionRepository.save(session), SessionDTO.class);
    }

    @Override
    public SessionDTO updateSession(Long id, SessionDTO sessionDTO) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Session found with ID: " + id));

        session.setNom(sessionDTO.getNom());
        session.setDateDebut(sessionDTO.getDateDebut());
        session.setDateFin(sessionDTO.getDateFin());

        return modelMapper.map(sessionRepository.save(session), SessionDTO.class);
    }

    @Override
    public void deleteSession(Long id) {
        if (!sessionRepository.existsById(id)) {
            throw new EntityNotFoundException("No Session found with ID: " + id);
        }
        sessionRepository.deleteById(id);
    }

    // If you want to implement permission handling like in EnseignantServiceImpl
    public List<SessionDTO> getSessionsForAuthenticatedUser(Authentication authentication) {
        // You can implement the user permission check similar to the EnseignantServiceImpl here.
        // For example, depending on user roles and permissions, you can filter which sessions they can access.
        throw new ForbiddenException("User does not have permission to view Sessions");
    }
}

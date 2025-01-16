package com.example.Surveillance.Services;

import com.example.Surveillance.Dtos.SessionDTO;
import java.util.List;

public interface SessionService {

    List<SessionDTO> getAllSessions();

    SessionDTO getSessionById(Long id);

    SessionDTO addSession(SessionDTO sessionDTO);

    SessionDTO updateSession(Long id, SessionDTO sessionDTO);

    void deleteSession(Long id);
}

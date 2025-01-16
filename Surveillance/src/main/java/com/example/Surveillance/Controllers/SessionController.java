package com.example.Surveillance.Controllers;

import com.example.Surveillance.Dtos.SessionDTO;
import com.example.Surveillance.Services.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
@AllArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT') or hasRole('ADMIN_DEPARTEMENT')")
    public ResponseEntity<List<SessionDTO>> getAllSessions() {
        List<SessionDTO> sessions = sessionService.getAllSessions();
        return ResponseEntity.ok(sessions);
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT') or hasRole('ADMIN_DEPARTEMENT')")
    public ResponseEntity<SessionDTO> addSession(@RequestBody SessionDTO sessionDTO) {
        SessionDTO createdSession = sessionService.addSession(sessionDTO);
        return ResponseEntity.ok(createdSession);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT') or hasRole('ADMIN_DEPARTEMENT')")
    public ResponseEntity<SessionDTO> getSessionById(@PathVariable Long id) {
        SessionDTO session = sessionService.getSessionById(id);
        return ResponseEntity.ok(session);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT') or hasRole('ADMIN_DEPARTEMENT')")
    public ResponseEntity<SessionDTO> updateSession(@PathVariable Long id, @RequestBody SessionDTO sessionDTO) {
        SessionDTO updatedSession = sessionService.updateSession(id, sessionDTO);
        return ResponseEntity.ok(updatedSession);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN_ETABLISSEMENT') or hasRole('ADMIN_DEPARTEMENT')")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseEntity.ok().build();
    }
}

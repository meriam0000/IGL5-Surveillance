package com.example.Surveillance.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveillanceDTO {
    private Long id; // Unique identifier for the surveillance
    private String nom; // Name of the surveillance
    private String jour; // Day of the surveillance
    private LocalDateTime dateDebut; // Start date and time of the surveillance
    private LocalDateTime dateFin; // End date and time of the surveillance
    private Long sessionId; // ID of the associated session (foreign key)
    @JsonIgnore
    private List<Long> sectionIds; // List of section IDs associated with the surveillance
    @JsonIgnore
    private Long salleId; // ID of the salle (classroom) where the surveillance takes place
}

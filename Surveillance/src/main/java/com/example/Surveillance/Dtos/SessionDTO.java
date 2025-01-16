package com.example.Surveillance.Dtos;

import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO {

    // Getters and Setters
    private Long id; // Unique identifier for the session
    private String nom; // Name of the session (e.g., "Main Exam", "Makeup Exam")
    private LocalDate dateDebut; // Start date of the session
    private LocalDate dateFin; // End date of the session

}

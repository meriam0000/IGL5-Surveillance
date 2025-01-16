package com.example.Surveillance.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the session

    @Column(nullable = false, length = 100)
    private String nom; // Name of the session (e.g., "Main Exam", "Makeup Exam")

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut; // Start date of the session

    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin; // End date of the session

}

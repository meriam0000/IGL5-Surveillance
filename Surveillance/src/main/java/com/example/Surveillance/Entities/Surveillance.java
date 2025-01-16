package com.example.Surveillance.Entities;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;  // Import the JsonIgnore annotation
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Surveillance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the surveillance

    @Column(nullable = false)
    private String nom; // Name of the surveillance (e.g., "Exam Surveillance")

    @Column(nullable = false)
    private String jour; // Day of the surveillance (e.g., "Monday")

    @Column(name = "date_debut", nullable = false)
    private LocalDateTime dateDebut; // Start date and time of the surveillance

    @Column(name = "date_fin", nullable = false)
    private LocalDateTime dateFin; // End date and time of the surveillance

    // Many-to-one relationship with Session (a surveillance is associated with one session)
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    @JsonIgnore  // Ignore the session in the serialization to avoid infinite recursion
    private Session session; // The session this surveillance is part of

    // Many-to-many relationship with Section (a surveillance can involve multiple sections)
    @ManyToMany
    @JoinTable(
            name = "surveillance_section",
            joinColumns = @JoinColumn(name = "surveillance_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    @JsonIgnore  // Ignore the sections in the serialization to avoid infinite recursion
    private List<Section> sections; // Sections involved in this surveillance

    // Many-to-one relationship with Salle (a surveillance happens in one salle)
    @ManyToOne
    @JoinColumn(name = "salle_id", nullable = false)
    @JsonIgnore  // Ignore the salle in the serialization to avoid infinite recursion
    private Salle salle; // The salle (classroom) where the surveillance takes place
}

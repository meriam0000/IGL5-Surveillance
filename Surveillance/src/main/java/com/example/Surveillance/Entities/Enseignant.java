package com.example.Surveillance.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "enseignants")
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the teacher

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;

    @Column(nullable = false, unique = true, length = 8)
    private String cin; // National ID or similar identifier

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "numero_telephone", nullable = false, unique = true, length = 15)
    private String numeroTelephone;

    @Column(nullable = false, length = 50)
    private String grade;

    @Column(name = "nb_heure_surveillance_maximale", nullable = false)
    private int nbHeureSurveillanceMaximale;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;


}

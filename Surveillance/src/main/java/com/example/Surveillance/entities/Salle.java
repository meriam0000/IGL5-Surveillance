package com.example.Surveillance.Entities;

import jakarta.persistence.*;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
public class Salle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nom;

    @Column
    private String type;

    @Column
    private int capacite;

    @ManyToOne
    @JoinColumn(name = "departement_id", nullable = false)
    @JsonBackReference
    private Departement departement;
}

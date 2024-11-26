package com.example.Surveillance.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "etablissement")
public class Etablissement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String localisation;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String universite;

    @JsonIgnore
    @OneToMany(mappedBy = "etablissement", cascade = CascadeType.ALL)
    private List<Departement> ListDepartement;

}




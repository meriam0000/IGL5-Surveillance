package com.example.Surveillance.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "departements")
@Data
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String nom;
    @Column(nullable= false ,length = 25)
    private String specialité ;
    @JsonIgnore
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private List<Enseignant> enseignants;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "etablissement_id")
    private Etablissement etablissement;
    @JsonIgnore
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private List<AdminDepartement> AdminDepartements;
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private List<Salle> salles;
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private List<Section> Sections;

}

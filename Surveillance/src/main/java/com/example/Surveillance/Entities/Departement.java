package com.example.Surveillance.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import com.example.Surveillance.Enums.Specialite;

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
    private Specialite specialité ;
    @JsonIgnore
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private List<Enseignant> enseignants;

}

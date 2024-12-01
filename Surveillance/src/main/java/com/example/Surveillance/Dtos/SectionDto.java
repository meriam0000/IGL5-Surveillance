package com.example.Surveillance.Dtos;

import lombok.Data;

@Data
public class SectionDto {
    private Long id;

    private String nom;

    private String specialite;

    private int niveau;

    private int nb_etudiants;

    private DepartementDto departement;
}

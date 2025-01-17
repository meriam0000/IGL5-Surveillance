package com.example.Surveillance.Dtos;

import lombok.Data;

@Data
public class EnseignantDto {
    private Long id;
    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String numeroTelephone;
    private String grade;
    private int nbHeureSurveillanceMaximale;
    private DepartementDto departement;
}

package com.example.Surveillance.Dtos;

import lombok.Data;

@Data
public class AdminDepartementDto {
    private Long id;
    private String nom;
    private String role;
    private DepartementDto departement; // Assuming AdminDepartement is related to Departement
}
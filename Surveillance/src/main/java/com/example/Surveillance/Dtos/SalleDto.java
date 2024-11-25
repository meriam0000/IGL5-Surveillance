package com.example.Surveillance.Dtos;

import com.example.Surveillance.Dtos.DepartementDto;
import lombok.Data;

@Data
public class SalleDto {

    private Long id;

    private String nom;

    private String type;

    private int capacite;

    private DepartementDto departement;
}

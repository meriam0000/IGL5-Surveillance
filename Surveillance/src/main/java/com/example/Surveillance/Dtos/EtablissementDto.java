package com.example.Surveillance.Dtos;

import com.example.Surveillance.Entities.Departement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

public class EtablissementDto {

    private Long id;


    private String localisation;


    private String nom;


    private String universite;

    @JsonIgnore
    private List<DepartementDto> ListDepartement;
}

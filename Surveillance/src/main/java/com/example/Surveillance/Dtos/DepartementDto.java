package com.example.Surveillance.Dtos;

import com.example.Surveillance.Entities.Enseignant;
import com.example.Surveillance.Enums.Specialite;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
@Data
public class DepartementDto {

    private Long id;
    private String nom;
    private Specialite specialité ;
    @JsonIgnore
    private List<EnseignantDto> enseignants;
}

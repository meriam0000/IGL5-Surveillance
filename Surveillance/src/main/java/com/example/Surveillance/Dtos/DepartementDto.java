package com.example.Surveillance.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.List;
@Data
public class DepartementDto {

    private Long id;
    private String nom;
    private String specialité ;
    private EtablissementDto etablissement;
    @JsonIgnore
    private List<EnseignantDto> enseignants;

}

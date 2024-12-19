    package com.example.Surveillance.Dtos;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import lombok.Data;

    import java.util.List;
    @Data
    public class EtablissementDto {

        private Long id;


        private String localisation;


        private String nom;


        private String universite;

        @JsonIgnore
        private List<DepartementDto> ListDepartement;
        @JsonIgnore
        private List<EnseignantDto> ListEnseignant;
    }

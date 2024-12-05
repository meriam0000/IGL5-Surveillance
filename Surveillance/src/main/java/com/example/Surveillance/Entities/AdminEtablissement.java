package com.example.Surveillance.Entities;

import com.example.Surveillance.Entities.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("ADMIN_ETABLISSEMENT")
public class AdminEtablissement extends User {

    @ManyToOne
    @JoinColumn(name = "etablissement_id", nullable = false)
    private Etablissement etablissement;

    // Additional fields or methods can go here, specific to AdminEtablissement if needed
}


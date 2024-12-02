package com.example.Surveillance.Entities;

import com.example.Surveillance.Entities.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdminDepartement extends User {
    @ManyToOne
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;

}

package com.example.Surveillance.Entities;

import com.example.Surveillance.Entities.user.User;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@Builder
public class SuperAdmin extends User {
    // Additional fields specific to SuperAdmin can be added here
}
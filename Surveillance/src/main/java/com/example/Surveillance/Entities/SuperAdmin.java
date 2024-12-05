package com.example.Surveillance.Entities;

import com.example.Surveillance.Entities.user.User;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("SUPER_ADMIN")
public class SuperAdmin extends User {
    // Additional fields or methods (if any) specific to SuperAdmin can go here
}

package com.example.Surveillance.Entities;

import com.example.Surveillance.Entities.user.User;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;


@Entity
@Data
@AllArgsConstructor

public class SuperAdmin extends User {


}

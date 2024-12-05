package com.example.Surveillance.Dtos;

import com.example.Surveillance.Entities.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDepartementDto {
    private Integer id;
    private String firstname;
    private String password;
    private String fullName;
    private String email;
    private Role role;
    private DepartementDto departement;

}

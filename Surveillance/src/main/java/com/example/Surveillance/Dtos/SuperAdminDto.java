package com.example.Surveillance.Dtos;

import com.example.Surveillance.Entities.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperAdminDto {
    private Integer id;
    private String firstname;
    private String fullName;
    private String password;
    private String email;
    private Role role;
}


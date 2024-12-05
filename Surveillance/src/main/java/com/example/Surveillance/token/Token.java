package com.example.Surveillance.token;

import com.example.Surveillance.Entities.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @Override
    public String toString() {
        return "Token{id=" + id + ", token='" + token + "', revoked=" + revoked + ", expired=" + expired + ", tokenType=" + tokenType + "}";
    }

}


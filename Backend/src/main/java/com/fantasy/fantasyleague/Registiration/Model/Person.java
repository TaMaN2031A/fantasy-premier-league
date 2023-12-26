package com.fantasy.fantasyleague.Registiration.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@MappedSuperclass
public class Person {
    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private String userName;
    private String region;
    private String firstName;
    private String lastName;
    private String password;
    private String token = "";
}
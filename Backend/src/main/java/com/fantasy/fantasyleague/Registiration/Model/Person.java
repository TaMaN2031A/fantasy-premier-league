package com.fantasy.fantasyleague.Registiration.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@MappedSuperclass
public class Person {

    @Id
    @Column(nullable = false,updatable = false)
    private String email;
    @Column(nullable = false, unique = true, updatable = false)
    private String userName;
    private String region;
    private String firstName;
    private String lastName;
    private String password;
    private String token="";
}

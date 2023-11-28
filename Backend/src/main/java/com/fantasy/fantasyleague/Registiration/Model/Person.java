package com.fantasy.fantasyleague.Registiration.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@MappedSuperclass
public class Person {

    @Id
    private String email;
    @Column(nullable = false, unique = true)
    private String userName;
    private String region;
    private String firstName;
    private String lastName;
    private String password;

}

package com.fantasy.fantasyleague.Registiration.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    private String userName;
    private String email;
    private String region;
    private String firstName;
    private String lastName;
    private String password;
    private String token;
}

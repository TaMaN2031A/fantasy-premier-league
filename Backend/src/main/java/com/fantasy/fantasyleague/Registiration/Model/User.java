package com.fantasy.fantasyleague.Registiration.Model;

import com.fantasy.fantasyleague.fantasyGame.Model.UserTeam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends Person {
    @OneToOne(mappedBy = "userName")
    private UserTeam team;

}

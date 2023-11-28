package com.fantasy.fantasyleague.Registiration.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User extends Person {
}

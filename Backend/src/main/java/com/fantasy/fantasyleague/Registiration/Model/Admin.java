package com.fantasy.fantasyleague.Registiration.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Entity

@Table(name = "admins")
public class Admin extends Person {
    public Admin(User user) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(user, this);
    }
    public Admin(){}

}

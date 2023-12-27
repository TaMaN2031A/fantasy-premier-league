package com.fantasy.fantasyleague.Registiration.Model;

import com.fantasy.fantasyleague.Group.Model.GroupFantasy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
public class User extends Person {
    private int points;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "groupFantasy_user_relationship",
            joinColumns = @JoinColumn(name = "user_name", referencedColumnName = "userName"),
            inverseJoinColumns = @JoinColumn(name = "groupFantasy_id")
    )
    private List<GroupFantasy> groupFantasies = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<GroupFantasy> ownedGroups = new ArrayList<>();
}

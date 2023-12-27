package com.fantasy.fantasyleague.Group.Model;

import com.fantasy.fantasyleague.Registiration.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupFantasy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupID;
    private String name;
    private int isPrivate;
    @ManyToOne
    @JoinColumn(name = "owner", nullable = false, referencedColumnName = "userName")
    private User owner;
    @ManyToMany
    @JoinTable(
            name = "groupFantasy_user_relationship",
            joinColumns = @JoinColumn(name = "groupFantasy_id"),
            inverseJoinColumns = @JoinColumn(name = "user_name", referencedColumnName = "userName")
    )
    @OrderBy("points ASC") // Order by points in ascending order
    private List<User> users = new ArrayList<>();


}

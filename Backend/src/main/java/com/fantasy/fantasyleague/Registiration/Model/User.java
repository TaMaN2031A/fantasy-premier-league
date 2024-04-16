package com.fantasy.fantasyleague.Registiration.Model;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.fantasyGame.Model.Formation.CurrentFormation;
import com.fantasy.fantasyleague.fantasyGame.Model.FormationStatusHistory.FormationStatusHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.FormationHistory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
//import com.fantasy.fantasyleague.Group.Model.GroupFantasy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
public class User extends Person implements Serializable {
    // @OneToOne(mappedBy = "userName", cascade = CascadeType.ALL)
    // private UserTeam team;
    @Column(nullable = true)
    private Boolean benchBoost = false;

    @Column(nullable = true)

    private Boolean tripleCaptain = false;
    @Column(nullable = true)
    private Double moneyRemaining = 100.0;
    @Column(nullable = true,name = "free_transfers")
    private int freeTransfers = 2;
    @Column(nullable = true,name = "points")
    private int points = 0;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "captain_id", referencedColumnName = "id")
    private Player captain ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vice_captain_id", referencedColumnName = "id")
    private Player viceCaptain;

//    @JsonIgnore
//    @ManyToMany
//    @JoinTable(
//            name = "groupFantasy_user_relationship",
//            joinColumns = @JoinColumn(name = "user_name", referencedColumnName = "userName"),
//            inverseJoinColumns = @JoinColumn(name = "groupFantasy_id")
//    )
//    private List<GroupFantasy> groupFantasies = new ArrayList<>();
//    @JsonIgnore
//    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
//    private List<GroupFantasy> ownedGroups = new ArrayList<>();

}

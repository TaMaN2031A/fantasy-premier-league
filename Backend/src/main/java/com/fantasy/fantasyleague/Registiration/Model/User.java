package com.fantasy.fantasyleague.Registiration.Model;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.fantasyGame.Model.Formation.CurrentFormation;
import com.fantasy.fantasyleague.fantasyGame.Model.FormationStatusHistory.FormationStatusHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.Formation;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends Person implements Serializable {
    // @OneToOne(mappedBy = "userName", cascade = CascadeType.ALL)
    // private UserTeam team;

    private Boolean benchBoost = false;
    private Boolean tripleCaptain = false;
    private Double moneyRemaining;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "captain_id", referencedColumnName = "id")
    private Player captain;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vice_captain_id", referencedColumnName = "id")
    private Player viceCaptain;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Formation> formations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<CurrentFormation> currentFormations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<FormationStatusHistory> formationStatusHistory;
}

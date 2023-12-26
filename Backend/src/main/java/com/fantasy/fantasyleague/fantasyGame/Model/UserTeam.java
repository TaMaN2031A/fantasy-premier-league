package com.fantasy.fantasyleague.fantasyGame.Model;


import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.fantasyGame.Model.History.Snapshot;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.Formation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.util.List;

@Entity
public class UserTeam {

    @Id
    @OneToOne
    @JoinColumn(name = "userName", referencedColumnName = "userName")
    private User userName;
    private boolean benchBoost;
    private boolean tripleCaptain;
    private double moneyRemaining;

    /*
    * - foreign key to captain and vice-captain of specific team
    * - handling of non-equal captain and vice-captain is done in the service layer
    * */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "captain_id", referencedColumnName = "id")
    private Player captain;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vice_captain_id", referencedColumnName = "id")
    private Player viceCaptain;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTeam")
    private List<Formation> formations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTeam")
    private List<Snapshot> snapshots;

}

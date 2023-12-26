package com.fantasy.fantasyleague.fantasyGame.Model;


import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.fantasyGame.Model.Formation.CurrentFormation;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.Formation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@Entity
public class UserTeam {

    @Id
    @OneToOne
    @JoinColumn(name = "userName", referencedColumnName = "userName")
    private User userName;
    private Boolean benchBoost = false;
    private Boolean tripleCaptain = false;
    private Double moneyRemaining;

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
    private List<CurrentFormation> currentFormations;

}

package com.fantasy.fantasyleague.fantasyGame.Model.FormationStatusHistory;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.fantasyGame.Model.UserTeam;
import jakarta.persistence.*;
@Entity
public class FormationStatusHistory {
    @Id
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    @JoinColumn(name = "UserName", referencedColumnName = "userName")
    private UserTeam userTeam;

    @Id
    private int week_no;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "captain_id", referencedColumnName = "id")
    private Player captain;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vice_captain_id", referencedColumnName = "id")
    private Player viceCaptain;

    private boolean benchBoost;
    private boolean tripleCaptain;
}

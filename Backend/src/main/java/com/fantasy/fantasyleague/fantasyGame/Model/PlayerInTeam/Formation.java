package com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam;


import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.fantasyGame.Model.History.Snapshot;
import com.fantasy.fantasyleague.fantasyGame.Model.UserTeam;
import jakarta.persistence.*;

import java.util.List;

@Entity
@IdClass(FormationComposite.class)
public class Formation {
    @Id
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @Id
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE )
    @JoinColumn(name = "UserName", referencedColumnName = "userName")
    private UserTeam userTeam;


    /*
    * associated attributes
    * */
    private boolean isStarter;
}

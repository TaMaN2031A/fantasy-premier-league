package com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam;


import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.fantasyGame.Model.UserTeam;
import jakarta.persistence.*;

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

    @Id
    private int weekNum;
    /*
    * associated attributes
    * */
    private boolean isStarter;
}

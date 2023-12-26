package com.fantasy.fantasyleague.fantasyGame.Model.History;
//
//import com.fantasy.fantasyleague.RealLeague.Model.Player;
//import com.fantasy.fantasyleague.fantasyGame.Model.UserTeam;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.PlayerMatchComposite;
import com.fantasy.fantasyleague.fantasyGame.Model.History.SnapshotComposite;
import com.fantasy.fantasyleague.fantasyGame.Model.UserTeam;
import jakarta.persistence.*;
import lombok.*;

/*
* - a problem is raised:
*       how to store formulations efficiently.
* */

@EqualsAndHashCode
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(SnapshotComposite.class)
public class Snapshot {
    @Id
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @Id
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    @JoinColumn(name = "UserName", referencedColumnName = "userName")
    private UserTeam userTeam;

//    @Id
//    private int weekNum;

    /*
    * associated variables
    * */
    private boolean isStarter;
}

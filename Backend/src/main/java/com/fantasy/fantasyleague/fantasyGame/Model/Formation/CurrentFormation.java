package com.fantasy.fantasyleague.fantasyGame.Model.Formation;
//
//import com.fantasy.fantasyleague.RealLeague.Model.Player;
//import com.fantasy.fantasyleague.fantasyGame.Model.UserTeam;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.Registiration.Model.User;
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
@IdClass(CurrentFormationComposite.class)
public class CurrentFormation {
    @Id
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @Id
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    @JoinColumn(name = "UserName", referencedColumnName = "userName")
    private User user;

    /*
    * associated variables
    * */
    private boolean isStarter;
}

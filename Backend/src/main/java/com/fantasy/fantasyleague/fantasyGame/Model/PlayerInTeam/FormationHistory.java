package com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam;


import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.Registiration.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(FormationHistoryComposite.class)
public class FormationHistory {
    @Id
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @Id
    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.MERGE )
    @JoinColumn(name = "UserName", referencedColumnName = "userName")
    private User user;

    @Id
    private int weekNum;
    /*
    * associated attributes
    * */
    private boolean isStarter;
}

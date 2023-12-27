package com.fantasy.fantasyleague.fantasyGame.Model.FormationStatusHistory;

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
@IdClass(FormationStatusHistoryComposite.class)
public class FormationStatusHistory {
    @Id
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    @JoinColumn(name = "UserName", referencedColumnName = "userName")
    private User user;

    @Id
    private int week_no;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "captain_id", referencedColumnName = "id", unique = false)
    private Player captain;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vice_captain_id", referencedColumnName = "id", unique = false)
    private Player viceCaptain;

    private boolean benchBoost;
    private boolean tripleCaptain;
}

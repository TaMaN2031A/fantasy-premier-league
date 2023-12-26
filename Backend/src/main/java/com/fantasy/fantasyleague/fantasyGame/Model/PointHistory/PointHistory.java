package com.fantasy.fantasyleague.fantasyGame.Model.PointHistory;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@IdClass(PointHistoryComposite.class)
public class PointHistory {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player playerPoint;

    @Id
    private int week_no;
    private int points;

}

package com.fantasy.fantasyleague.fantasyGame.Model.PointHistory;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
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
@IdClass(PointHistoryComposite.class)
public class PointHistory {

    @Id
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL )
    @JoinColumn(name = "player_id", referencedColumnName = "id",updatable = false)
    private Player playerPoint;

    @Id
    private int week_no;
    private int points;

}

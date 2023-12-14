package com.fantasy.fantasyleague.RealLeague.Model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Data
@Builder
@Entity
@Table(name = "PlayerStatistics")
@IdClass(PlayerMatchComposite.class)
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStatistics {

    @Id
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @Id
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.MERGE )
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private PlayedMatch match;

    @Builder.Default
    private int goal = 0;
    @Builder.Default
    private boolean man_of_match = false;
    @Builder.Default
    private int yellowCards = 0;
    @Builder.Default
    private int redCards = 0;
    @Builder.Default
    private int saves = 0;
    @Builder.Default
    private int assists = 0;
    @Builder.Default
    private int effectivness = 0;


}

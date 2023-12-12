package com.fantasy.fantasyleague.RealLeague.Model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Data
@Builder
@Entity
@Table(name = "PlayerStatistics")
@IdClass(PalyerMatchComposite.class)
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

    private int goal;
    private boolean man_of_match;
    private int yellowCards;
    private int redCards;
    private int saves;
    private int assists;
    private int effectivness;




}

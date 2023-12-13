package com.fantasy.fantasyleague.RealLeague.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode
@Data
@Builder
@Entity
@Table(name = "PlayedMatch")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlayedMatch {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int ID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "home_id" , referencedColumnName = "id")
    private Team home;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "away_id" , referencedColumnName = "id")
    private Team away;

    @Builder.Default
    private Date date = new Date();
    @Builder.Default
    private int week = 0;


    @Builder.Default
    private int yellowCardsHome =0;
    @Builder.Default
    private int redCardsHome =0;
    @Builder.Default
    private int yellowCardsAway =0;
    @Builder.Default
    private int redCardsAway = 0;
    @Builder.Default
    private int awayGoals =0;
    @Builder.Default
    private int homeGoals = 0;
    @Builder.Default
    private String stadium = "";

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
    private List<PlayerStatistics> playersStatistics;

}

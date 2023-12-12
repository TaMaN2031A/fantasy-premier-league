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
    @JoinColumn(name = "home" , referencedColumnName = "id")
    private Team home;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "away" , referencedColumnName = "id")
    private Team away;


    private Date date;
    private int week;



    private int yellowCardsHome;
    private int redCardsHome;
    private int yellowCardsAway;
    private int redCardsAway;
    private int awayGoals;
    private int homeGoals;
    private String stadium;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
    private List<PlayerStatistics> playersStatistics;













}

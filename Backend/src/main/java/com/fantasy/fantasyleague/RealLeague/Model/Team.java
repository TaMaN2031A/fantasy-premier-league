package com.fantasy.fantasyleague.RealLeague.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Team {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int ID;
    @Column(unique = true, updatable = false)
    private String name;
    private int red_cards;
    private int yellow_cards;
    private int goals_for;
    private int goals_conceded;
    private int goals_difference;
    private int points;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "home")
    private List<PlayedMatch> homeMatches;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "away")
    private List<PlayedMatch> awayMatches;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private List<Player> players;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "home")
    private List<UpcomingMatch> upcomingMatchesHome;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "away")
    private List<UpcomingMatch> upcomingMatchesAway;
    public Team(String name) {
        this.name = name;
        this.red_cards = 0;
        this.yellow_cards = 0;
        this.goals_for = 0;
        this.goals_conceded = 0;
        this.goals_difference = 0;
        this.points = 0;
    }
}
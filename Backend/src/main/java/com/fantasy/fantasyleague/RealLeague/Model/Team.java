package com.fantasy.fantasyleague.RealLeague.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int ID;
    private String name;
    private int red_cards;
    private int yellow_cards;
    private int goals_for;
    private int goals_conceded;
    private int goals_difference;
    private int points;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private List<Player> players;
    public Team(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.red_cards = 0;
        this.yellow_cards = 0;
        this.goals_for = 0;
        this.goals_conceded = 0;
        this.goals_difference = 0;
        this.points = 0;
    }
}
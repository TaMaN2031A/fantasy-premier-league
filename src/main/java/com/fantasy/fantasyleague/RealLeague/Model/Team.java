package com.fantasy.fantasyleague.RealLeague.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int ID;
    private String name;
    private int red_cards;
    private int yellow_cards;
    private int goals_for;
    private int goals_conceded;
    private int goals_difference;
    private int points;
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRed_cards() {
        return red_cards;
    }

    public void setRed_cards(int red_cards) {
        this.red_cards = red_cards;
    }

    public int getYellow_cards() {
        return yellow_cards;
    }

    public void setYellow_cards(int yellow_cards) {
        this.yellow_cards = yellow_cards;
    }

    public int getGoals_for() {
        return goals_for;
    }

    public void setGoals_for(int goals_for) {
        this.goals_for = goals_for;
    }

    public int getGoals_conceded() {
        return goals_conceded;
    }

    public void setGoals_conceded(int goals_conceded) {
        this.goals_conceded = goals_conceded;
    }

    public int getGoals_difference() {
        return goals_difference;
    }

    public void setGoals_difference(int goals_difference) {
        this.goals_difference = goals_difference;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

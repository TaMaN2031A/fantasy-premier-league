package com.fantasy.fantasyleague.RealLeague.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Getter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int ID;
    @Getter
    private String name;
    @Getter
    private int red_cards;
    @Getter
    private int yellow_cards;
    @Getter
    private int goals;
    @Getter
    private int assists;
    @Getter
    private int saved;
    @Getter
    private String position;
    @Getter
    private int number_in_team;

    public void setId_of_team(int id_of_team) {
        this.id_of_team = id_of_team;
    }

    @Getter
    private int id_of_team;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;
    @Getter
    private String photo_link;
    @Getter
    private double price;
    public Player(String name, String position, int number_in_team, int id_of_team) {
        this.name = name;
        this.red_cards = 0;
        this.yellow_cards = 0;
        this.goals = 0;
        this.assists = 0;
        this.saved = 0;
        this.position = position;
        this.number_in_team = number_in_team;
        this.price = 0;
        this.id_of_team = id_of_team;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRed_cards(int red_cards) {
        this.red_cards = red_cards;
    }

    public void setYellow_cards(int yellow_cards) {
        this.yellow_cards = yellow_cards;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setSaved(int saved) {
        this.saved = saved;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setNumber_in_team(int number_in_team) {
        this.number_in_team = number_in_team;
    }

    public Team getTeam_id() {
        return team;
    }

    public void setTeam_id(Team team) {
        this.team = team;
    }

    public void setPhoto_link(String photo_link) {
        this.photo_link = photo_link;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
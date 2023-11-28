package com.fantasy.fantasyleague.RealLeague.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int ID;
    private String name;
    private int red_cards;
    private int yellow_cards;
    private int goals;
    private int assists;
    private int saved;
    private String position;
    private int number_in_team;
    private int id_of_team;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;
    private String photo_link;
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

}
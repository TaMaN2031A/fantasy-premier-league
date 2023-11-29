package com.fantasy.fantasyleague.Player;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Player {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int ID;
    @Setter
    @Getter
    private String name;
    @Getter
    @Setter
    private int red_cards;
    @Setter
    @Getter
    private int yellow_cards;
    @Getter
    @Setter
    private int goals;
    @Getter
    @Setter
    private int assists;
    @Getter
    @Setter
    private int saved;
    @Getter
    @Setter
    private char position;
    @Getter
    @Setter
    private int number_in_team;
    @Setter
    @Getter
    private int team_id;
    @Getter
    @Setter
    private String photo_link;
    @Setter
    @Getter
    private double price;
    public Player(String name, int red_cards, int yellow_cards, int goals, int assists, int saved, char position, int number_in_team, int team_id, String photo_link, double price) {
        this.name = name;
        this.red_cards = red_cards;
        this.yellow_cards = yellow_cards;
        this.goals = goals;
        this.assists = assists;
        this.saved = saved;
        this.position = position;
        this.number_in_team = number_in_team;
        this.team_id = team_id;
        this.photo_link = photo_link;
        this.price = price;
    }
}

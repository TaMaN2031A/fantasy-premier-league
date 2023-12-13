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
public class Player {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    private List<PlayerStatistics> playersStatistics;
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
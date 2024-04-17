package com.fantasy.fantasyleague.RealLeague.DTO;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TeamDTO {
    private String name;
    private String logo;
    private int red_cards;
    private int yellow_cards;
    private int goals_for;
    private int goals_conceded;
    private int goals_difference;
    private int points;
    public Team getTeam() {
        return Team.builder()
                .name(name)
                .logo(logo)
                .red_cards(red_cards)
                .yellow_cards(yellow_cards)
                .goals_for(goals_for)
                .goals_conceded(goals_conceded)
                .goals_difference(goals_difference)
                .points(points)
                .build();
    }
}


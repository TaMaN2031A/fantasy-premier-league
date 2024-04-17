package com.fantasy.fantasyleague.RealLeague.DTO;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import lombok.*;
import org.springframework.context.annotation.Bean;

@Setter
@Getter
@Builder
public class PlayerDTO {
    private String name;
    private String position;
    private int number_in_team;
    private int id_of_team;
    // add price, cards, goals, assists, clean_sheet, saved, photo_link
     private String photo_link;
     private int goals;
     private int assists;
     private int clean_sheet;
     private int saves;
     private int yellow_cards;
     private int red_cards;
     private double price;

    public Player getPlayer(){
        return Player.builder()
                .name(name)
                .position(position)
                .number_in_team(number_in_team)
                .id_of_team(id_of_team)
                .photo_link(photo_link)
                .goals(goals)
                .assists(assists)
                .clean_sheet(clean_sheet)
                .saved(saves)
                .yellow_cards(yellow_cards)
                .red_cards(red_cards)
                .price(price)
                .build();
    }
}

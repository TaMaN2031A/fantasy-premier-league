package com.fantasy.fantasyleague.RealLeague.DTO;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private String name;
    private String position;
    private int number_in_team;
    private int id_of_team;
    public Player getPlayer(){
        return new Player(
                this.getName(),
                this.getPosition(),
                this.getNumber_in_team(),
                this.getId_of_team()
        );
    }
}

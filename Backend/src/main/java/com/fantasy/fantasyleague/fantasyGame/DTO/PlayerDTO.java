package com.fantasy.fantasyleague.fantasyGame.DTO;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayerDTO {
    private PlayerWithoutPoints player;
    private int points;

}

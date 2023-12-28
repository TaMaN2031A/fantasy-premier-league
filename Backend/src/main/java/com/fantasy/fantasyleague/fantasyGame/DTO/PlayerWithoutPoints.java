package com.fantasy.fantasyleague.fantasyGame.DTO;

import com.fantasy.fantasyleague.RealLeague.DTO.PlayerDTO;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PlayerWithoutPoints {
    private Player player;
    private String teamName;
    private boolean isStarter;
}

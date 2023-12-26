package com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.fantasyGame.Model.UserTeam;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@EqualsAndHashCode
public class FormationComposite implements Serializable {
    private int player;
    private String userTeam;
    private int weekNum;
}

package com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
@EqualsAndHashCode
public class FormationHistoryComposite implements Serializable {
    private int player;
    private String user;
    private int weekNum;
}

package com.fantasy.fantasyleague.fantasyGame.Model.History;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.Formation;
import com.fantasy.fantasyleague.fantasyGame.Model.UserTeam;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode
public class SnapshotComposite implements Serializable {
    private Player player;
    private UserTeam userTeam;
//    private Integer weekNum;

}

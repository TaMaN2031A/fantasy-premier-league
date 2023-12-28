package com.fantasy.fantasyleague.fantasyGame.DTO;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class FormationDTO {
    private List<PlayerDTO> players;
    private boolean BenchBoost;
    private boolean TripleCaptain;
    private Player Captain;
    private Player ViceCaptain;
    private int totalPoints;

    public FormationDTO(List<PlayerDTO> players,Player Captain, Player ViceCaptain,boolean BenchBoost, boolean TripleCaptain){
        this.BenchBoost = BenchBoost;
        this.TripleCaptain = TripleCaptain;
        this.Captain = Captain;
        this.ViceCaptain = ViceCaptain;
        this.players = players;
        int totalPoints = 0,viceCaptainIndex = -1,multiplier = 2;
//        if(TripleCaptain)
//            multiplier = 3;
//        else
//            multiplier = 2;
        boolean captainStarted = false;
        for(PlayerDTO player:players){
            if(player.getPlayer().isStarter()){
                player.setPoints(player.getPoints()+2);
                if(player.getPlayer().equals(Captain)){
                    captainStarted = true;
                    player.setPoints(player.getPoints()*multiplier);
                }
            }
        }
        if(!captainStarted&&viceCaptainIndex!=-1){
            players.get(viceCaptainIndex).setPoints(players.get(viceCaptainIndex).getPoints()*multiplier);
        }
        for(PlayerDTO player:players){
            if(player.getPlayer().isStarter())
                totalPoints+= player.getPoints();
//            else if(BenchBoost)
//                totalPoints+= player.getPoints();
        }
        this.totalPoints = totalPoints;
    }
}

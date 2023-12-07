package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.DTO.PlayerDTO;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    ResponseEntity insertPlayer(PlayerDTO playerDTO);
    ResponseEntity updatePlayer(Player player);
    ResponseEntity deletePlayer(String id);
    ResponseEntity deleteAllPlayers();
}
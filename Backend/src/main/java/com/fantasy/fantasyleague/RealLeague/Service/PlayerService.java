package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.Model.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    String insertPlayer(Player player);
    String updatePlayer(Player player);
    String deletePlayer(Player player);
    String deleteAllPlayers();
}
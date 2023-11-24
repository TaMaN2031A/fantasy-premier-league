package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlayerServiceImpl implements PlayerService{
    private String deleteResponse = "Deleted Successfully";
    private String insertResponse = "Inserted Successfully";
    private String updateResponse = "Updated Successfully";
    private String deleteResponseF = "Unsuccessful Delete";
    private String insertResponseF = "Unsuccessful Insert";
    private String updateResponseF = "Unsuccessful Update";
    @Autowired
    PlayerRepository playerRepository;
    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public String insertPlayer(Player player) {
        playerRepository.save(player);
        return insertResponse;
    }

    @Override
    public String updatePlayer(Player player) {
        if(!playerRepository.existsById(player.getID()))
            return updateResponseF;
        Player playerToUpdate = playerRepository.getReferenceById(player.getID());
        playerToUpdate.setName(player.getName()); // We'll add more functionalities in next phases
        playerToUpdate.setAssists(player.getAssists());
        playerToUpdate.setGoals(player.getGoals());
        playerToUpdate.setTeam_id(player.getTeam_id());
        playerToUpdate.setNumber_in_team(player.getNumber_in_team());
        playerToUpdate.setPhoto_link(player.getPhoto_link());
        playerToUpdate.setPosition(player.getPosition());
        playerToUpdate.setPrice(player.getPrice());
        playerToUpdate.setRed_cards(player.getRed_cards());
        playerToUpdate.setSaved(player.getSaved());
        playerToUpdate.setYellow_cards(player.getYellow_cards());
        playerRepository.save(playerToUpdate);
        return updateResponse;
    }

    @Override
    public String deletePlayer(Player player) {
        if(!playerRepository.existsById(player.getID()))
            return deleteResponseF;
        playerRepository.deleteById(player.getID());
        return deleteResponse;
    }

    @Override
    public String deleteAllPlayers() {
        playerRepository.deleteAll();
        return deleteResponse;
    }
}

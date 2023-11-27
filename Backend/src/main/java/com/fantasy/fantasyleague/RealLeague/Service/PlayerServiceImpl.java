package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.*;
import com.fantasy.fantasyleague.RealLeague.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @Autowired
    TeamRepository teamRepository;
    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public String insertPlayer(Player player) {
        Optional<Team> to_be_linked =  teamRepository.findById(player.getId_of_team());
        if(to_be_linked.isPresent())
        {
            Team team = to_be_linked.get();
            for(Player pl: team.getPlayers()){
                if(pl.getNumber_in_team() == player.getNumber_in_team()){
                    return insertResponseF; // Cannot insert if same number
                }
            }
            player.setTeam_id(team);
            if(player.getNumber_in_team()<1||player.getNumber_in_team()>99)
                return insertResponseF;
            playerRepository.save(player);
            return insertResponse;
        }
        return insertResponseF;
    }

    @Override
    public String updatePlayer(Player player) {
        if(!playerRepository.existsById(player.getID()))
            return updateResponseF;
        Player playerToUpdate = playerRepository.getReferenceById(player.getID());
        playerToUpdate.setName(player.getName());
        playerToUpdate.setAssists(player.getAssists());
        playerToUpdate.setGoals(player.getGoals());
        Optional<Team> to_be_linked =  teamRepository.findById(player.getId_of_team());
        if(to_be_linked.isPresent())
        {
            Team team = to_be_linked.get();
            for(Player pl: team.getPlayers()){
                if(pl.getNumber_in_team() == player.getNumber_in_team() && pl.getID() != player.getID()){
                    return insertResponseF; // Cannot insert if same number, here I should exclude him
                }
            }
            playerToUpdate.setNumber_in_team(player.getNumber_in_team());
            playerToUpdate.setTeam_id(team);
        }else{
            // User sent a wrong id team number
            return updateResponseF;
        }
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
    // We'll delete all players if a team deleted
    @Override
    public String deleteAllPlayers() {
        playerRepository.deleteAll();
        return deleteResponse;
    }
}
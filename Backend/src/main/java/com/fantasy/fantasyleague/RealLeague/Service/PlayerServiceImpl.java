package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.DTO.PlayerDTO;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.*;
import com.fantasy.fantasyleague.RealLeague.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    TeamRepository teamRepository;


    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public ResponseEntity insertPlayer(PlayerDTO playerDTO) {
        Map<String, String> response = new HashMap<>();
        try{
            Optional<Team> toBeLinked = teamRepository.findById(playerDTO.getId_of_team());

            if (toBeLinked.isPresent()) {
                Team team = toBeLinked.get();
                for (Player pl : team.getPlayers()) {
                    if(pl.getNumber_in_team() == playerDTO.getNumber_in_team()){
                        response.put("error", "There is a player having this number");
                        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                    }
                }
                if (playerDTO.getNumber_in_team() < 1 || playerDTO.getNumber_in_team() > 99) {
                    response.put("error", "Give a number between 1 to 99");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
                System.out.println(playerDTO.toString());
                Player player = playerDTO.getPlayer();
                player.setTeam(team);
                playerRepository.save(player);
                response.put("message", "Player inserted successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("error", "Failed to insert player");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            response.put("error", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @Override
    public ResponseEntity updatePlayer(Player player) {
        Map<String, String> response = new HashMap<>();
        try{
            if(!playerRepository.existsById(player.getID())){
                response.put("error", "Player doesn't exist");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
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
                        response.put("error", "There is a player having this number");
                        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                    }
                }
                if (player.getNumber_in_team() < 1 || player.getNumber_in_team() > 99) {
                    response.put("error", "Give a number between 1 to 99");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
                playerToUpdate.setNumber_in_team(player.getNumber_in_team());
                playerToUpdate.setTeam(team);
            }else{
                // User sent a wrong id team number
                response.put("error", "Wrong team id");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            playerToUpdate.setPhoto_link(player.getPhoto_link());
            playerToUpdate.setPosition(player.getPosition());
            playerToUpdate.setPrice(player.getPrice());
            playerToUpdate.setRed_cards(player.getRed_cards());
            playerToUpdate.setSaved(player.getSaved());
            playerToUpdate.setYellow_cards(player.getYellow_cards());
            playerRepository.save(playerToUpdate);
            response.put("message", "Player updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("error", e.toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity deletePlayer(String ID) {
        Map<String, String> response = new HashMap<>();
        try{
            int id = Integer.parseInt(ID);
            if(!playerRepository.existsById(id)) {
                response.put("error", "Player ID don't exist");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            playerRepository.deleteById(id);
            response.put("message", "Player deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("message", e.toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
    // We'll delete all players if a team deleted
    @Override
    public ResponseEntity deleteAllPlayers() {
        Map<String, String> response = new HashMap<>();
        try{
            playerRepository.deleteAll();
            response.put("message", "All Players deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("error", e.toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
}
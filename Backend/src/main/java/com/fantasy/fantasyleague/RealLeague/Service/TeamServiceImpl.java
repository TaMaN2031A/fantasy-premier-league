package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.DTO.PlayerDTO;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository teamRepository;
    @Override
    public ResponseEntity insertTeam(String name) {
        Map<String, String> response = new HashMap<>();
        try {
            Team team1 = teamRepository.findByName(name);
            if(team1 != null) {
                response.put("error", "Team Already Exists");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            teamRepository.save(new Team(name));
            response.put("message", "Team inserted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("error", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity updateTeam(int id, String newName) {
        Map<String, String> response = new HashMap<>();
        try {
            if(!teamRepository.existsById(id)) {
                response.put("error", "Team doesn't exist");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            Team teamToUpdate = teamRepository.getReferenceById(id);
            teamToUpdate.setName(newName);
            response.put("message", "Team Updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("error", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity deleteTeam(String ID) {
        Map<String, String> response = new HashMap<>();
        try {
            int id = Integer.parseInt(ID);
            if(!teamRepository.existsById(id)) {
                response.put("error", "Team doesn't exist");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            teamRepository.deleteById(id);
            response.put("message", "Team deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("error", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    @Override
    public ResponseEntity deleteAllTeam(){
        Map<String, String> response = new HashMap<>();
        try{
            teamRepository.deleteAll();
            response.put("message", "All Teams deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("error", e.toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.Model.Team;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();
    ResponseEntity insertTeam(String name);
    ResponseEntity updateTeam(int id, String newName);
    ResponseEntity deleteTeam(String ID);
    ResponseEntity deleteAllTeam();
}
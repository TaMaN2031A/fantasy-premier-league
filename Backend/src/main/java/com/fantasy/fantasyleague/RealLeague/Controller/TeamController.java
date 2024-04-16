package com.fantasy.fantasyleague.RealLeague.Controller;

import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;
    @PostMapping("/insert/{name}")
    public ResponseEntity insertTeam(@PathVariable String name) {
        return teamService.insertTeam(name);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeam(@PathVariable String id) {
        return teamService.deleteTeam(id);
    }
    @PostMapping("/update/{id}/{name}")
    public ResponseEntity updateTeam(@PathVariable String id, @PathVariable String name) {
        return teamService.updateTeam(id, name);
    }

    @PostMapping("/insert/{id}/{logo}")
    public ResponseEntity addTeamLogo(@PathVariable String id, @PathVariable String logo) {
        return teamService.addTeamLogo(id, logo);
    }

    @GetMapping("/getAll")
    public List<Team> getAllTeam() {
        return teamService.getAllTeams();
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAllTeam() {
        return teamService.deleteAllTeam();
    }
}
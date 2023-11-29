package com.fantasy.fantasyleague.RealLeague.Controller;

import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;
    @PostMapping("/insert")
    public String insertTeam(@RequestBody Team team) {
        return teamService.insertTeam(team);
    }
    @DeleteMapping("/delete")
    public String deleteTeam(@RequestBody Team team) {
        return teamService.deleteTeam(team);
    }
    @PostMapping("/update")
    public String updateTeam(@RequestBody Team team) {
        return teamService.updateTeam(team);
    }
    @GetMapping("/getAll")
    public List<Team> getAllTeam() {
        return teamService.getAllTeams();
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllTeam() {
        return teamService.deleteAllTeam();
    }
}
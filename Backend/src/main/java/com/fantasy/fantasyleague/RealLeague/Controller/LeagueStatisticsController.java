package com.fantasy.fantasyleague.RealLeague.Controller;

import com.fantasy.fantasyleague.RealLeague.DTO.TopPlayer;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Service.LeagueStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/leagueStatistics")
public class LeagueStatisticsController {
    @Autowired
    LeagueStatisticsService leagueStatisticsService;

    @GetMapping("/getLeagueStandings")
    public List<Team> getLeagueStandings() {
        return leagueStatisticsService.getLeagueStandings();
    }

    @GetMapping("/getTopScorers")
    public List<TopPlayer> getTopScorers() {
        return leagueStatisticsService.getTopScorers();
    }

    @GetMapping("/getTopAssists")
    public List<TopPlayer> getTopAssists() {
        return leagueStatisticsService.getTopAssists();
    }

    @GetMapping("/getTopCleanSheets")
    public List<TopPlayer> getTopCleanSheets() {
        return leagueStatisticsService.getTopCleanSheets();
    }
}

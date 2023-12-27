package com.fantasy.fantasyleague.RealLeague.Controller;

import com.fantasy.fantasyleague.RealLeague.DTO.TopPlayer;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import com.fantasy.fantasyleague.RealLeague.Service.LeagueStatisticsService;
import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.Model.Role;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.FormationHistory;
import com.fantasy.fantasyleague.fantasyGame.Repository.FormationHistoryRepo;
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
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FormationHistoryRepo formationHistoryRepo;


    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;
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

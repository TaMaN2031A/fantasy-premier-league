package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.Model.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();
    String insertTeam(Team team);
    String updateTeam(Team team);
    String deleteTeam(Team team);
    String deleteAllTeam();
}

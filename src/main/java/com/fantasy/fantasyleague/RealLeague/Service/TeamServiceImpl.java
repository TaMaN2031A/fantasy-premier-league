package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private String deleteResponse = "Deleted Successfully";
    private String insertResponse = "Inserted Successfully";
    private String updateResponse = "Updated Successfully";
    private String deleteResponseF = "Unsuccessful Delete";
    private String insertResponseF = "Unsuccessful Insert";
    private String updateResponseF = "Unsuccessful Update";

    @Autowired
    TeamRepository teamRepository;
    @Override
    public String insertTeam(Team team) {
        Team team1 = teamRepository.findByName(team.getName()).orElse(null);
        if(team1 == null)//Search by name
            return insertResponseF;
        teamRepository.save(team);
        return insertResponse;
    }

    @Override
    public String updateTeam(Team team) {
        if(!teamRepository.existsById(team.getID()))
            return updateResponseF;
        Team teamToUpdate = teamRepository.getReferenceById(team.getID());
        teamToUpdate.setName(team.getName()); // We'll add more functionalities in next phases
        teamRepository.save(teamToUpdate);
        return updateResponse;
    }

    @Override
    public String deleteTeam(Team team) {
        if(!teamRepository.existsById(team.getID()))
            return deleteResponseF;
        teamRepository.deleteById(team.getID());
        return deleteResponse;
    }

    @Override
    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    @Override
    public String deleteAllTeam(){
        teamRepository.deleteAll();
        return deleteResponse;
    }
}

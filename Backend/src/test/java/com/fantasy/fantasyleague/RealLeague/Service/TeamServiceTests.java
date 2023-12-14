package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTests {
    @Mock
    private TeamRepository teamRepository;
    @InjectMocks
    private TeamServiceImpl teamService;

    @Test
    public void TeamService_InsertTeamSuccessfully_ReturnResponseEntity200(){
        Team team = new Team("Damanhour");
        when(teamRepository.findByName(anyString())).thenReturn(null);
        when(teamRepository.save(Mockito.any(Team.class))).thenReturn(team);
        ResponseEntity response = teamService.insertTeam("Damanhour");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void TeamService_InsertTeamUnsuccessfullyAsTeamExists_ReturnResponseEntity400(){
        when(teamRepository.findByName(anyString())).thenReturn(new Team());
        ResponseEntity response = teamService.insertTeam("Damanhour");
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void TeamService_UpdateTeamSuccessfully_ReturnResponseEntity200(){
        Team team = new Team("Damanhour");
        Team teamToUpdate = new Team("Somouha");

        when(teamRepository.existsById(anyInt())).thenReturn(true);
        when(teamRepository.getReferenceById(anyInt())).thenReturn(team);
        when(teamRepository.save(Mockito.any(Team.class))).thenReturn(teamToUpdate);

        ResponseEntity response = teamService.updateTeam(String.valueOf(anyInt()), "Somouha");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void TeamService_UpdateTeamUnsuccessfullyAsIDDoesntExist_ReturnResponseEntity404(){
        Team team = new Team("Damanhour");

        when(teamRepository.existsById(team.getID())).thenReturn(false);
        ResponseEntity response = teamService.updateTeam(String.valueOf(team.getID()), "Somouha");

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }

    @Test
    public void TeamService_UpdateTeamNotGivingAnIntegerString_ReturnResponseEntity500(){

        ResponseEntity response = teamService.updateTeam("NaN", "Somouha");

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void TeamService_DeleteTeamSuccessfully_Returns200(){

        when(teamRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(teamRepository).deleteById(anyInt());

        ResponseEntity response = teamService.deleteTeam(String.valueOf(anyInt()));

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void TeamService_DeleteTeamUnsuccessfully_ReturnsNotFound404(){


        when(teamRepository.existsById(anyInt())).thenReturn(false);

        ResponseEntity response = teamService.deleteTeam(String.valueOf(anyInt()));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void TeamService_DeleteTeamUnsuccessfully_ReturnsInternalServerError500(){


        ResponseEntity response = teamService.deleteTeam("NaN");
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    public void TeamService_GetAllTeam_ReturnsListOfTeams(){
        Team team = new Team("Alahly");
        Team team1 = new Team("AlZamalek");
        List<Team> teamList = new ArrayList<>();
        teamList.add(team1); teamList.add(team);


        when(teamRepository.findAll()).thenReturn(teamList);


        Assertions.assertEquals(teamList, teamService.getAllTeams());

    }

    @Test
    public void TeamService_DeleteAllTeams_Returns200(){

        doNothing().when(teamRepository).deleteAll();

        ResponseEntity response = teamService.deleteAllTeam();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}


package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.DTO.PlayerDTO;
import com.fantasy.fantasyleague.RealLeague.DTO.UpcomingMatchInsertionDTO;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Model.UpcomingMatch;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.UpcomingMatchRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.UpcomingMatchRepositoryTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpcomingMatchServiceTest {
    @Mock
    private TeamRepository teamRepository;
    @Mock
    private UpcomingMatchRepository upcomingMatchRepository;
    @InjectMocks
    private UpcomingMatchServiceImpl upcomingMatchService;

    private String validId;
    private String notValidId;
    private UpcomingMatch upcomingMatch;
    private UpcomingMatch upcomingMatch2;
    private UpcomingMatchInsertionDTO upcomingMatchInsertionDTO;
    private Team teamWithSameShirtNumber;
    private Team teamWithSuccessfulInsert;
    private List<UpcomingMatch> list = new ArrayList<>();


    @BeforeEach
    public void init(){
        validId = "101";
        notValidId = "AnyStringWithCharacters";
       // player = new Player("Eyad Games", Position.MID.name(), 21, 1);
       // player2 = new Player("Abo Treika", Position.MID.name(), 22, 1);
        upcomingMatchInsertionDTO = new UpcomingMatchInsertionDTO(1, 1, 2, "Borg EL Arab");

       // list.add(player); list.add(player2);
       // list1.add(player);
    }

    @Test
    public void UpcomingService_InsertUpcomingSuccessfully_ReturnResponseEntity200(){
        when(teamRepository.existsById(anyInt())).thenReturn(true);
        when(teamRepository.getReferenceById(anyInt())).thenReturn(new Team("Team"));
        when(teamRepository.getReferenceById(anyInt())).thenReturn(new Team("team"));

        when(upcomingMatchRepository.save(Mockito.any(UpcomingMatch.class))).thenReturn(null);

        ResponseEntity response = upcomingMatchService.
                insertUpComingMatch(upcomingMatchInsertionDTO);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void UpcomingMatchService_InsertPlayerUnSuccessfullyWrongTeamID_ReturnResponseEntity400(){
        when(teamRepository.existsById(anyInt())).thenReturn(false);

        ResponseEntity response = upcomingMatchService.
                insertUpComingMatch(upcomingMatchInsertionDTO);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void UpcomingMatchService_InsertPlayerUnSuccessfullyWrongWeekNumber_ReturnResponseEntity400(){
        when(teamRepository.existsById(anyInt())).thenReturn(true);

        upcomingMatchInsertionDTO.setWeek(-50);
        ResponseEntity response = upcomingMatchService.
                insertUpComingMatch(upcomingMatchInsertionDTO);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    public void UpcomingMatchService_DeleteTeamSuccessfully_Returns200(){

        when(upcomingMatchRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(upcomingMatchRepository).deleteById(anyInt());

        ResponseEntity response = upcomingMatchService.deleteUpcomingMatch(String.valueOf(anyInt()));

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void UpcomingMatchService_DeleteTeamUnsuccessfully_ReturnsNotFound404(){


        when(upcomingMatchRepository.existsById(anyInt())).thenReturn(false);

        ResponseEntity response = upcomingMatchService.deleteUpcomingMatch(String.valueOf(anyInt()));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void UpcomingMatchService_DeleteTeamUnsuccessfully_ReturnsInternalServerError500(){

        ResponseEntity response = upcomingMatchService.deleteUpcomingMatch("Nan");

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    public void UpcomingMatchService_GetAllMatches_ReturnsListOfMatches(){
        UpcomingMatch upcomingMatch1 = new UpcomingMatch();
        UpcomingMatch upcomingMatch3 = new UpcomingMatch();
        List<UpcomingMatch> upcomingMatchesList = new ArrayList<>();
        upcomingMatchesList.add(upcomingMatch1); upcomingMatchesList.add(upcomingMatch3);


        when(upcomingMatchRepository.findAll()).thenReturn(upcomingMatchesList);


        Assertions.assertEquals(upcomingMatchesList, upcomingMatchService.getAllUpcomingMatch());

    }

    @Test
    public void UpcomingMatchService_GetAllUpcomingMatchesInWeek_ReturnsListOfPlayers(){
        UpcomingMatch upcomingMatch1 = new UpcomingMatch();
        UpcomingMatch upcomingMatch3 = new UpcomingMatch();
        upcomingMatch3.setWeek(2); upcomingMatch3.setWeek(1);
        List<UpcomingMatch> upcomingMatchesList = new ArrayList<>();
        upcomingMatchesList.add(upcomingMatch1); upcomingMatchesList.add(upcomingMatch3);

        List<UpcomingMatch> list1 = new ArrayList<>();
        list1.add(upcomingMatch1);
        when(upcomingMatchRepository.findByWeek(2)).thenReturn(list1);


        Assertions.assertEquals(list1, upcomingMatchService.getAllUpcomingMatch(2));

    }

    @Test
    public void UpcomingMatchService_DeleteAllUpcomingMatch_Returns200(){

        doNothing().when(upcomingMatchRepository).deleteAll();

        ResponseEntity response = upcomingMatchService.deleteAllUpcomingMatch();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void UpcomingMatchService_getUpcomingMatch_ReturnsNull(){


        UpcomingMatch upcomingMatch1 = upcomingMatchService.getMatch(anyInt());
        when(upcomingMatchRepository.existsById(anyInt())).thenReturn(false);
        Assertions.assertNull(upcomingMatchService.getMatch(anyInt()));
    }

    @Test
    public void UpcomingMatchService_getUpcomingMatch_ReturnsUpcomingMatch(){
        UpcomingMatch upcomingMatch1 = upcomingMatchService.getMatch(anyInt());
        when(upcomingMatchRepository.existsById(anyInt())).thenReturn(true);
        Assertions.assertNull(upcomingMatchService.getMatch(1));
    }


}


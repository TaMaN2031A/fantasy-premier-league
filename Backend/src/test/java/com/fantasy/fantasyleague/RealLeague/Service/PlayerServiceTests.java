package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.DTO.PlayerDTO;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Position;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
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
public class PlayerServiceTests {
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private TeamRepository teamRepository;
    @InjectMocks
    private PlayerServiceImpl playerService;

    private String validId;
    private String notValidId;
    private Player player;
    private Player player2;
    private Player player3;
    private PlayerDTO playerDTO;
    private Team emptyTeam;
    private Team teamWithSameShirtNumber;
    private Team teamWithSuccessfulInsert;
    private List<Player> list = new ArrayList<>();
    private List<Player> list1 = new ArrayList<>();


    @BeforeEach
    public void init(){
        validId = "101";
        notValidId = "AnyStringWithCharacters";
        player = new Player("Eyad Games", "AMF", 21, 1);
        player2 = new Player("Abo Treika", "AMF", 22, 1);
        playerDTO = new PlayerDTO("Eyad Games", "AMF", 21, 1);

        list.add(player); list.add(player2);
        list1.add(player);
        emptyTeam = new Team("Al Ahly");
        teamWithSameShirtNumber = new Team("Al Zamalek");
        teamWithSameShirtNumber.setPlayers(list);
    }

    @Test
    public void PlayerService_InsertPlayerSuccessfully_ReturnResponseEntity200(){
        when(playerRepository.save(Mockito.any(Player.class))).thenReturn(
                new Player("Mohamed Zidan", Position.FWD.name(), 2, teamWithSameShirtNumber.getID()));
        when(teamRepository.findById(anyInt())).thenReturn(Optional.of(teamWithSameShirtNumber));

        ResponseEntity response = playerService.
                insertPlayer(new PlayerDTO("Mohamed Zidan", Position.FWD.name(), 2, teamWithSameShirtNumber.getID()));
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void PlayerService_InsertPlayerUnSuccessfullySameShirtNumber_ReturnResponseEntity400(){
        when(teamRepository.findById(anyInt())).thenReturn(Optional.of(teamWithSameShirtNumber));

        ResponseEntity response = playerService.
                insertPlayer(new PlayerDTO("Mohamed Zidan", Position.FWD.name(), 22, teamWithSameShirtNumber.getID()));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void PlayerService_InsertPlayerUnSuccessfullyWrongTShirtNumber_ReturnResponseEntity400(){
        when(teamRepository.findById(anyInt())).thenReturn(Optional.of(teamWithSameShirtNumber));

        ResponseEntity response = playerService.
                insertPlayer(new PlayerDTO("Mohamed Zidan", Position.FWD.name(), -1, teamWithSameShirtNumber.getID()));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void PlayerService_InsertPlayerUnSuccessfullyWrongTeamID_ReturnResponseEntity404(){
        when(teamRepository.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity response = playerService.
                insertPlayer(new PlayerDTO("Mohamed Zidan", Position.FWD.name(), -1, teamWithSameShirtNumber.getID()));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void PlayerService_UpdatePlayerSuccessfully_ReturnResponseEntity200(){
        when(playerRepository.save(Mockito.any(Player.class))).thenReturn(
                new Player("Mohamed Zidan", Position.FWD.name(), 2, teamWithSameShirtNumber.getID()));
        when(teamRepository.findById(anyInt())).thenReturn(Optional.of(teamWithSameShirtNumber));
        when(playerRepository.existsById(anyInt())).thenReturn(true);
        when(playerRepository.getReferenceById(anyInt())).thenReturn(
                new Player("Mohamed Zidan", Position.FWD.name(), 2, teamWithSameShirtNumber.getID())
        );

        ResponseEntity response = playerService.
                updatePlayer(new Player("Mohamed Zidan", Position.FWD.name(), 2, teamWithSameShirtNumber.getID()));
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void PlayerService_UpdatePlayerUnsuccessfullyBecauseOfWrongPlayerID_ReturnResponseEntity404(){
        when(playerRepository.existsById(anyInt())).thenReturn(false);
        ResponseEntity response = playerService.
                updatePlayer(new Player("Mohamed Zidan", Position.FWD.name(), 2, teamWithSameShirtNumber.getID()));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void PlayerService_UpdatePlayerUnSuccessfullySameShirtNumber_ReturnResponseEntity400(){
        when(teamRepository.findById(anyInt())).thenReturn(Optional.of(teamWithSameShirtNumber));
        when(playerRepository.existsById(anyInt())).thenReturn(true);
        ResponseEntity response = playerService.
                updatePlayer(new Player("Mohamed Zidan", Position.FWD.name(), 22, teamWithSameShirtNumber.getID()));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void PlayerService_UpdatePlayerUnSuccessfullyWrongTShirtNumber_ReturnResponseEntity400(){
        when(teamRepository.findById(anyInt())).thenReturn(Optional.of(teamWithSameShirtNumber));
        when(playerRepository.existsById(anyInt())).thenReturn(true);

        ResponseEntity response = playerService.
                updatePlayer(new Player("Mohamed Zidan", Position.FWD.name(), -1, teamWithSameShirtNumber.getID()));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void PlayerService_UpdatePlayerUnSuccessfullyWrongTeamID_ReturnResponseEntity404(){
        when(teamRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(playerRepository.existsById(anyInt())).thenReturn(true);

        ResponseEntity response = playerService.
                updatePlayer(new Player("Mohamed Zidan", Position.FWD.name(), -1, teamWithSameShirtNumber.getID()));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    public void PlayerService_DeleteTeamSuccessfully_Returns200(){

        when(playerRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(playerRepository).deleteById(anyInt());

        ResponseEntity response = playerService.deletePlayer(String.valueOf(anyInt()));

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void PlayerService_DeleteTeamUnsuccessfully_ReturnsNotFound404(){


        when(playerRepository.existsById(anyInt())).thenReturn(false);

        ResponseEntity response = playerService.deletePlayer(String.valueOf(anyInt()));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void TeamService_DeleteTeamUnsuccessfully_ReturnsInternalServerError500(){


        ResponseEntity response = playerService.deletePlayer("NaN");
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    @Test
    public void TeamService_GetAllPlayers_ReturnsListOfPlayers(){
        Player player = new Player("Ibrahim El Nabulsi", Position.FWD.name(), 19, 1);
        Player player2 = new Player("Sameeh Abo El Wafa", Position.FWD.name(), 19, 1);

        List<Player> playerList = new ArrayList<>();
        playerList.add(player); playerList.add(player2);


        when(playerRepository.findAll()).thenReturn(playerList);


        Assertions.assertEquals(playerList, playerService.getAllPlayers());

    }

    @Test
    public void TeamService_DeleteAllPlayers_Returns200(){

        doNothing().when(playerRepository).deleteAll();

        ResponseEntity response = playerService.deleteAllPlayers();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}


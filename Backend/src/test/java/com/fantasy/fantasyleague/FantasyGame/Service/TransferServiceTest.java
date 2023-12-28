package com.fantasy.fantasyleague.FantasyGame.Service;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Position;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.fantasyGame.DTO.CompleteTeam;
import com.fantasy.fantasyleague.fantasyGame.Model.Lock;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.FormationHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.WeekNo;
import com.fantasy.fantasyleague.fantasyGame.Repository.FormationHistoryRepo;
import com.fantasy.fantasyleague.fantasyGame.Repository.WeekNoRepo;
import com.fantasy.fantasyleague.fantasyGame.Service.TransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Stream;

import static com.fantasy.fantasyleague.Registiration.SharedServices.SharedServices.generateUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {
    @InjectMocks
    private TransferService transferService;

    @Mock
    private UserRepository userRepo;

    @Mock
    private WeekNoRepo weekNoRepo;

    @Mock
    private PlayerRepository playerRepo;

    @Mock
    private FormationHistoryRepo formationHistoryRepo;
    public CompleteTeam generateCompleteTeam() {
        String username = "exampleUser";
        List<String> players = List.of("Player1", "Player2", "Player3");
        CompleteTeam completeTeam = new CompleteTeam();
        completeTeam.setUsername(username);
        completeTeam.setPlayers(players);
        return completeTeam;
    }

    @Test
    void confirmTransfer_userNotFound_returnsInternalServerError() {
        CompleteTeam completeTeam = new CompleteTeam("username", null);
        when(userRepo.findByUserName("username")).thenReturn(Optional.empty());

        ResponseEntity<String> response = transferService.confirmTransfer(completeTeam);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("no such user", response.getBody());
    }
    @Test
    public void testValidTransferConditions() {
        List<Player> validPlayers = Arrays.asList(
                new Player("player1", "GK", 10, 1),
                new Player("player2", "GK", 10, 1),
                new Player("player3", "DEF", 10, 1),
                new Player("player4", "DEF", 10, 1),
                new Player("player5", "DEF", 10, 1),
                new Player("player6", "DEF", 10, 1),
                new Player("player6", "DEF", 10, 1),
                new Player("player7", "MID", 10, 1),
                new Player("player8", "MID", 10, 1),
                new Player("player9", "MID", 10, 1),
                new Player("player10", "MID", 10, 1),
                new Player("player11", "MID", 10, 1),
                new Player("player12", "FWD", 10, 1),
                new Player("player13", "FWD", 10, 1),
                new Player("player14", "FWD", 10, 1)
        );
        for(int i=0;i<validPlayers.size();i++){
            validPlayers.get(i).setID(i);
        }

        assertTrue(transferService.manageTransferConditions(validPlayers));
    }

    @Test
    public void testInvalidGoalkeeperCount() {
        List<Player> InValidPlayers = Arrays.asList(
                new Player("player1", "GK", 10, 1),
                new Player("player2", "GK", 10, 1),
                new Player("player3", "GK", 10, 1),
                new Player("player4", "DEF", 10, 1),
                new Player("player5", "DEF", 10, 1),
                new Player("player6", "DEF", 10, 1),
                new Player("player6", "DEF", 10, 1),
                new Player("player7", "MID", 10, 1),
                new Player("player8", "MID", 10, 1),
                new Player("player9", "MID", 10, 1),
                new Player("player10", "MID", 10, 1),
                new Player("player11", "MID", 10, 1),
                new Player("player12", "FWD", 10, 1),
                new Player("player13", "FWD", 10, 1),
                new Player("player14", "FWD", 10, 1)
        );
        for(int i=0;i<InValidPlayers.size();i++){
            InValidPlayers.get(i).setID(i);
        }

        assertFalse(transferService.manageTransferConditions(InValidPlayers));
    }

    @Test
    public void testInvalidDefenderCount() {
        List<Player> InValidPlayers = Arrays.asList(
                new Player("player1", "GK", 10, 1),
                new Player("player2", "GK", 10, 1),
                new Player("player3", "DEF", 10, 1),
                new Player("player4", "DEF", 10, 1),
                new Player("player5", "DEF", 10, 1),
                new Player("player6", "DEF", 10, 1),
                new Player("player6", "DEF", 10, 1),
                new Player("player7", "MID", 10, 1),
                new Player("player8", "MID", 10, 1),
                new Player("player9", "MID", 10, 1),
                new Player("player10", "MID", 10, 1),
                new Player("player11", "MID", 10, 1),
                new Player("player12", "MID", 10, 1),
                new Player("player13", "FWD", 10, 1),
                new Player("player14", "FWD", 10, 1)
        );
        for(int i=0;i<InValidPlayers.size();i++){
            InValidPlayers.get(i).setID(i);
        }

        assertFalse(transferService.manageTransferConditions(InValidPlayers));    }

    @Test
    public void testInvalidTotalPrice() {
        List<Player> InValidPlayers = Arrays.asList(
                new Player("player1", "GK", 10, 1),
                new Player("player2", "GK", 10, 1),
                new Player("player3", "GK", 10, 1),
                new Player("player4", "DEF", 10, 1),
                new Player("player5", "DEF", 10, 1),
                new Player("player6", "DEF", 10, 1),
                new Player("player6", "DEF", 10, 1),
                new Player("player7", "MID", 10, 1),
                new Player("player8", "MID", 10, 1),
                new Player("player9", "MID", 10, 1),
                new Player("player10", "MID", 10, 1),
                new Player("player11", "MID", 10, 1),
                new Player("player12", "FWD", 10, 1),
                new Player("player13", "FWD", 10, 1),
                new Player("player14", "FWD", 10, 1)
        );
        for (Player inValidPlayer : InValidPlayers) {
            inValidPlayer.setPrice(100);
        }

        assertFalse(transferService.manageTransferConditions(InValidPlayers));
    }

    @Test
    public void testCollectPlayers() {
        // Mock playerRepo behavior for each player name
        when(playerRepo.findByName("player1")).thenReturn(new Player("player1", "GK", 10, 1));
        when(playerRepo.findByName("player2")).thenReturn(new Player("player2", "DF", 10, 2));
        when(playerRepo.findByName("player3")).thenReturn(new Player("player3", "MF", 10, 3));

        List<String> team = Arrays.asList("player1", "player2", "player3");

        List<Player> result = transferService.collectPlayers(team);

        assertEquals(3, result.size());

        // Verify that the correct players were added to the result list
        assertTrue(result.contains(new Player("player1", "GK", 10, 1)));
        assertTrue(result.contains(new Player("player2", "DF", 10, 2)));
        assertTrue(result.contains(new Player("player3", "MF", 10, 3)));
    }

    @Test
    public void testCollectPlayersWithMissingPlayers() {
        // Mock playerRepo behavior for each player name
        when(playerRepo.findByName("player1")).thenReturn(new Player("player1", "GK", 10, 1));
        // Not mocking "player2" and "player3" intentionally to simulate missing players

        List<String> team = Arrays.asList("player1", "player2", "player3");

        List<Player> result = transferService.collectPlayers(team);

        assertEquals(1, result.size());  // Only one player should be collected

        // Verify that the correct player was added to the result list
        assertTrue(result.contains(new Player("player1", "GK", 10, 1)));

        // Verify that missing players were not added to the result list
        verify(playerRepo, times(1)).findByName("player1");
        verify(playerRepo, times(1)).findByName("player2");
        verify(playerRepo, times(1)).findByName("player3");
    }

    @Test
    void testGetPlayersWithValidPosition() {
        // Mock data
        List<Player> goalkeepers = List.of(new Player("John", "GK", 10, 1));
        when(playerRepo.findByPosition("GK")).thenReturn(goalkeepers);

        // Test
        List<Player> result = transferService.getPlayers("GK");

        // Assertions
        assertNotNull(result);
        assertEquals(goalkeepers, result);
    }

    @Test
    void testGetPlayersWithInvalidPosition() {
        // Test
        List<Player> result = transferService.getPlayers("Invalid");

        // Assertions
        assertNull(result);
    }

    @Test
    void testGetGoalKeepers() {
        // Mock data
        List<Player> goalkeepers = List.of(new Player("John", "GK", 10, 1));
        when(playerRepo.findByPosition("GK")).thenReturn(goalkeepers);

        // Test
        List<Player> result = transferService.getPlayers("GK");

        // Assertions
        assertNotNull(result);
        assertEquals(goalkeepers, result);
    }

    @Test
    void testGetDefenders() {
        // Mock data
        List<Player> defenders = List.of(new Player("Bob", "DEF",10, 1));
        when(playerRepo.findByPosition("DEF")).thenReturn(defenders);

        // Test
        List<Player> result = transferService.getPlayers("DEF");

        // Assertions
        assertNotNull(result);
        assertEquals(defenders, result);
    }

    @Test
    void testGetMidfielders() {
        // Mock data
        List<Player> midfielders = List.of(new Player("Alice", "MID", 10, 1));
        when(playerRepo.findByPosition("MID")).thenReturn(midfielders);

        // Test
        List<Player> result = transferService.getPlayers("MID");

        // Assertions
        assertNotNull(result);
        assertEquals(midfielders, result);
    }

    @Test
    void testGetForwards() {
        // Mock data
        List<Player> forwards = List.of(new Player("Charlie", "FWD", 10, 1));
        when(playerRepo.findByPosition("FWD")).thenReturn(forwards);

        // Test
        List<Player> result = transferService.getPlayers("FWD");

        // Assertions
        assertNotNull(result);
        assertEquals(forwards, result);
    }
}

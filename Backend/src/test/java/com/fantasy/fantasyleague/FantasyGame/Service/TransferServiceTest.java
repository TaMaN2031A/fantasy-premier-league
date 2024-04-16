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
                new Player("player1", Position.GK.name(), 10, 1),
                new Player("player2", Position.GK.name(), 10, 1),
                new Player("player3", Position.DEF.name(), 10, 1),
                new Player("player4", Position.DEF.name(), 10, 1),
                new Player("player5", Position.DEF.name(), 10, 1),
                new Player("player6", Position.DEF.name(), 10, 1),
                new Player("player6", Position.DEF.name(), 10, 1),
                new Player("player7", Position.MID.name(), 10, 1),
                new Player("player8", Position.MID.name(), 10, 1),
                new Player("player9", Position.MID.name(), 10, 1),
                new Player("player10", Position.MID.name(), 10, 1),
                new Player("player11", Position.MID.name(), 10, 1),
                new Player("player12", Position.FWD.name(), 10, 1),
                new Player("player13", Position.FWD.name(), 10, 1),
                new Player("player14", Position.FWD.name(), 10, 1)
        );
        for(int i=0;i<validPlayers.size();i++){
            validPlayers.get(i).setID(i);
        }

        assertTrue(transferService.manageTransferConditions(validPlayers));
    }

    @Test
    public void testInvalidGoalkeeperCount() {
        List<Player> InValidPlayers = Arrays.asList(
                new Player("player1", Position.GK.name(), 10, 1),
                new Player("player2", Position.GK.name(), 10, 1),
                new Player("player3", Position.GK.name(), 10, 1),
                new Player("player4", Position.DEF.name(), 10, 1),
                new Player("player5", Position.DEF.name(), 10, 1),
                new Player("player6", Position.DEF.name(), 10, 1),
                new Player("player6", Position.DEF.name(), 10, 1),
                new Player("player7", Position.MID.name(), 10, 1),
                new Player("player8", Position.MID.name(), 10, 1),
                new Player("player9", Position.MID.name(), 10, 1),
                new Player("player10", Position.MID.name(), 10, 1),
                new Player("player11", Position.MID.name(), 10, 1),
                new Player("player12", Position.FWD.name(), 10, 1),
                new Player("player13", Position.FWD.name(), 10, 1),
                new Player("player14", Position.FWD.name(), 10, 1)
        );
        for(int i=0;i<InValidPlayers.size();i++){
            InValidPlayers.get(i).setID(i);
        }

        assertFalse(transferService.manageTransferConditions(InValidPlayers));
    }

    @Test
    public void testInvalidDefenderCount() {
        List<Player> InValidPlayers = Arrays.asList(
                new Player("player1", Position.GK.name(), 10, 1),
                new Player("player2", Position.GK.name(), 10, 1),
                new Player("player3", Position.DEF.name(), 10, 1),
                new Player("player4", Position.DEF.name(), 10, 1),
                new Player("player5", Position.DEF.name(), 10, 1),
                new Player("player6", Position.DEF.name(), 10, 1),
                new Player("player6", Position.DEF.name(), 10, 1),
                new Player("player7", Position.MID.name(), 10, 1),
                new Player("player8", Position.MID.name(), 10, 1),
                new Player("player9", Position.MID.name(), 10, 1),
                new Player("player10", Position.MID.name(), 10, 1),
                new Player("player11", Position.MID.name(), 10, 1),
                new Player("player12", Position.MID.name(), 10, 1),
                new Player("player13", Position.FWD.name(), 10, 1),
                new Player("player14", Position.FWD.name(), 10, 1)
        );
        for(int i=0;i<InValidPlayers.size();i++){
            InValidPlayers.get(i).setID(i);
        }

        assertFalse(transferService.manageTransferConditions(InValidPlayers));    }

    @Test
    public void testInvalidTotalPrice() {
        List<Player> InValidPlayers = Arrays.asList(
                new Player("player1", Position.GK.name(), 10, 1),
                new Player("player2", Position.GK.name(), 10, 1),
                new Player("player3", Position.GK.name(), 10, 1),
                new Player("player4", Position.DEF.name(), 10, 1),
                new Player("player5", Position.DEF.name(), 10, 1),
                new Player("player6", Position.DEF.name(), 10, 1),
                new Player("player6", Position.DEF.name(), 10, 1),
                new Player("player7", Position.MID.name(), 10, 1),
                new Player("player8", Position.MID.name(), 10, 1),
                new Player("player9", Position.MID.name(), 10, 1),
                new Player("player10", Position.MID.name(), 10, 1),
                new Player("player11", Position.MID.name(), 10, 1),
                new Player("player12", Position.FWD.name(), 10, 1),
                new Player("player13", Position.FWD.name(), 10, 1),
                new Player("player14", Position.FWD.name(), 10, 1)
        );
        for (Player inValidPlayer : InValidPlayers) {
            inValidPlayer.setPrice(100);
        }

        assertFalse(transferService.manageTransferConditions(InValidPlayers));
    }

    @Test
    public void testCollectPlayers() {
        // Mock playerRepo behavior for each player name
        when(playerRepo.findByName("player1")).thenReturn(new Player("player1", Position.GK.name(), 10, 1));
        when(playerRepo.findByName("player2")).thenReturn(new Player("player2", Position.DEF.name(), 10, 2));
        when(playerRepo.findByName("player3")).thenReturn(new Player("player3", Position.MID.name(), 10, 3));

        List<String> team = Arrays.asList("player1", "player2", "player3");

        List<Player> result = transferService.collectPlayers(team);

        assertEquals(3, result.size());

        // Verify that the correct players were added to the result list
        assertTrue(result.contains(new Player("player1", Position.GK.name(), 10, 1)));
        assertTrue(result.contains(new Player("player2", Position.DEF.name(), 10, 2)));
        assertTrue(result.contains(new Player("player3", Position.MID.name(), 10, 3)));
    }

    @Test
    public void testCollectPlayersWithMissingPlayers() {
        // Mock playerRepo behavior for each player name
        when(playerRepo.findByName("player1")).thenReturn(new Player("player1", Position.GK.name(), 10, 1));
        // Not mocking "player2" and "player3" intentionally to simulate missing players

        List<String> team = Arrays.asList("player1", "player2", "player3");

        List<Player> result = transferService.collectPlayers(team);

        assertEquals(1, result.size());  // Only one player should be collected

        // Verify that the correct player was added to the result list
        assertTrue(result.contains(new Player("player1", Position.GK.name(), 10, 1)));

        // Verify that missing players were not added to the result list
        verify(playerRepo, times(1)).findByName("player1");
        verify(playerRepo, times(1)).findByName("player2");
        verify(playerRepo, times(1)).findByName("player3");
    }

    @Test
    void testGetPlayersWithValidPosition() {
        // Mock data
        List<Player> goalkeepers = List.of(new Player("John", Position.GK.name(), 10, 1));
        when(playerRepo.findByPosition(Position.GK.name())).thenReturn(goalkeepers);

        // Test
        List<Player> result = transferService.getPlayers(Position.GK.name());

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
        List<Player> goalkeepers = List.of(new Player("John", Position.GK.name(), 10, 1));
        when(playerRepo.findByPosition(Position.GK.name())).thenReturn(goalkeepers);

        // Test
        List<Player> result = transferService.getPlayers(Position.GK.name());

        // Assertions
        assertNotNull(result);
        assertEquals(goalkeepers, result);
    }

    @Test
    void testGetDefenders() {
        // Mock data
        List<Player> defenders = List.of(new Player("Bob", Position.DEF.name(),10, 1));
        when(playerRepo.findByPosition(Position.DEF.name())).thenReturn(defenders);

        // Test
        List<Player> result = transferService.getPlayers(Position.DEF.name());

        // Assertions
        assertNotNull(result);
        assertEquals(defenders, result);
    }

    @Test
    void testGetMidfielders() {
        // Mock data
        List<Player> midfielders = List.of(new Player("Alice", Position.MID.name(), 10, 1));
        when(playerRepo.findByPosition(Position.MID.name())).thenReturn(midfielders);

        // Test
        List<Player> result = transferService.getPlayers(Position.MID.name());

        // Assertions
        assertNotNull(result);
        assertEquals(midfielders, result);
    }

    @Test
    void testGetForwards() {
        // Mock data
        List<Player> forwards = List.of(new Player("Charlie", Position.FWD.name(), 10, 1));
        when(playerRepo.findByPosition(Position.FWD.name())).thenReturn(forwards);

        // Test
        List<Player> result = transferService.getPlayers(Position.FWD.name());

        // Assertions
        assertNotNull(result);
        assertEquals(forwards, result);
    }
}

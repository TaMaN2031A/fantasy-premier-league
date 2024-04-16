package com.fantasy.fantasyleague.FantasyGame.Service;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Position;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.fantasyGame.DTO.FormationWithoutPointsDTO;
import com.fantasy.fantasyleague.fantasyGame.DTO.PlayerWithoutPoints;
import com.fantasy.fantasyleague.fantasyGame.Model.Formation.CurrentFormation;
import com.fantasy.fantasyleague.fantasyGame.Repository.CurrentFormationRepo;
import com.fantasy.fantasyleague.fantasyGame.Service.PickTeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.fantasy.fantasyleague.Registiration.SharedServices.SharedServices.generateUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PickTeamServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private CurrentFormationRepo currentFormationRepo;

    @InjectMocks
    private PickTeamService pickTeamService;

    @Test
    void saveFormation_success() {
        // Arrange
        FormationWithoutPointsDTO formationDTO = getFormationWithoutPointsDTO();
        User user;
        user = generateUser("amin@gmail.com", "amin", "Egypt", "amin", "elzainy", "123456");
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));

        // Act
        ResponseEntity<String> response = pickTeamService.SaveFormation(user.getUserName(), formationDTO);

        // Assert
        assertEquals(HttpStatus.OK, ( response).getStatusCode());
        assertEquals("Formation Saved", response.getBody());
        verify(userRepository).save(user);
        verify(currentFormationRepo).saveAll(anyList());
    }

    private static FormationWithoutPointsDTO getFormationWithoutPointsDTO() {
        Player player = new Player("amin", Position.GK.name(), 1, 1);
        Player player1 = new Player("amin", Position.MID.name(), 2, 1);
        List<PlayerWithoutPoints> playerWithoutPointsList = new ArrayList<>();
        PlayerWithoutPoints playerWithoutPoints = new PlayerWithoutPoints(player, "Arsenal", true);
        PlayerWithoutPoints playerWithoutPoints1 = new PlayerWithoutPoints(player1, "Liverpool", false);
        playerWithoutPointsList.add(playerWithoutPoints);
        playerWithoutPointsList.add(playerWithoutPoints1);

        FormationWithoutPointsDTO formationDTO = new FormationWithoutPointsDTO(playerWithoutPointsList,player,player1,true,false);
        return formationDTO;
    }

    // ... other test cases for SaveFormation (user not found, exception handling)

    @Test
    void getUserFantasyTeamFormation_success() {
        // Arrange
        Team team = new Team("Liverpool");
        Player player = new Player("amin", Position.GK.name(), 1, 1);
        Player player1 = new Player("amin", Position.MID.name(), 2, 1);
        player.setTeam(team);
        player1.setTeam(team);
        User user = generateUser("mady@gmail.com", "mady", "Egypt", "mady", "elzainy", "123456");
        List<CurrentFormation> currentFormations = new ArrayList<>();
        CurrentFormation currentFormation = new CurrentFormation(player, user, true);
        CurrentFormation currentFormation1 = new CurrentFormation(player1, user, false);
        currentFormations.add(currentFormation);
        currentFormations.add(currentFormation1);
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
        when(currentFormationRepo.findByUser(anyString())).thenReturn(currentFormations);

        // Act
        ResponseEntity<FormationWithoutPointsDTO> response = pickTeamService.GetUserFantasyTeamFormation(user.getUserName());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

        FormationWithoutPointsDTO formationDTO = response.getBody();
        assertNotNull(formationDTO);

        List<PlayerWithoutPoints> players = formationDTO.getPlayers();
        assertEquals(2, players.size());

        // Assert player 1
        PlayerWithoutPoints playerDTO1 = players.get(0);
        assertEquals(player.getID(), playerDTO1.getPlayer().getID());
        assertEquals(player.getPosition(), playerDTO1.getPlayer().getPosition());
        assertTrue(playerDTO1.isStarter());

        // Assert player 2
        PlayerWithoutPoints playerDTO2 = players.get(1);
        assertEquals(player1.getID(), playerDTO2.getPlayer().getID());
        assertEquals(player1.getPosition(), playerDTO2.getPlayer().getPosition());
        assertFalse(playerDTO2.isStarter());
    }
    @Test
    void getUserFantasyTeamFormation_userNotFound() {
        // Arrange
        String nonExistingUsername = "nonExistingUser";
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<FormationWithoutPointsDTO> response = pickTeamService.GetUserFantasyTeamFormation(nonExistingUsername);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getUserFantasyTeamFormation_internalServerError() {
        // Arrange
        String existingUsername = "existingUser";
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(new User()));
        when(currentFormationRepo.findByUser(anyString())).thenThrow(new RuntimeException("Simulated exception"));

        // Act
        ResponseEntity<FormationWithoutPointsDTO> response = pickTeamService.GetUserFantasyTeamFormation(existingUsername);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void saveFormation_userNotFound() {
        // Arrange
        String nonExistingUsername = "nonExistingUser";
        FormationWithoutPointsDTO formationDTO = getFormationWithoutPointsDTO();

        when(userRepository.findByUserName(anyString())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<String> response = pickTeamService.SaveFormation(nonExistingUsername, formationDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void saveFormation_internalServerError() {
        // Arrange
        String existingUsername = "existingUser";
        FormationWithoutPointsDTO formationDTO = getFormationWithoutPointsDTO();

        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(new User()));
        when(currentFormationRepo.saveAll(any())).thenThrow(new RuntimeException("Simulated exception"));

        // Act
        ResponseEntity<String> response = pickTeamService.SaveFormation(existingUsername, formationDTO);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

}

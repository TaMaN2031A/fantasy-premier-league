package com.fantasy.fantasyleague.FantasyGame.Service;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.fantasyGame.DTO.FormationDTO;
import com.fantasy.fantasyleague.fantasyGame.Model.Formation.CurrentFormation;
import com.fantasy.fantasyleague.fantasyGame.Model.FormationStatusHistory.FormationStatusHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.FormationHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.PointHistory.PointHistory;
import com.fantasy.fantasyleague.fantasyGame.Repository.CurrentFormationRepo;
import com.fantasy.fantasyleague.fantasyGame.Repository.FormationHistoryRepo;
import com.fantasy.fantasyleague.fantasyGame.Repository.FormationStatusHistoryRepo;
import com.fantasy.fantasyleague.fantasyGame.Service.FormationHistoryService;
import com.fantasy.fantasyleague.fantasyGame.Service.PointHistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.fantasy.fantasyleague.Registiration.SharedServices.SharedServices.generateUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FormationHistoryServiceTest {

    @Mock
    private FormationHistoryRepo formationHistoryRepo;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CurrentFormationRepo currentFormationRepo;

    @Mock
    private PointHistoryService pointHistoryService;

    @Mock
    private FormationStatusHistoryRepo formationStatusHistoryRepo;

    @InjectMocks
    private FormationHistoryService formationHistoryService;

    @Test
    public void testGetFormationHistoryByUserAndWeek() {
        Player player = new Player("amin", "GK", 1, 1);
        Player player1 = new Player("amr", "MID", 2, 1);
        player.setTeam(new Team("Liverpool"));
        player1.setTeam(new Team("Arsenal"));
        User user = generateUser("madyelzainy@gmail.com", "abdo", "Egypt", "mady", "elzainy", "123456");
        List<FormationHistory> formationHistoryList = new ArrayList<>();
        formationHistoryList.add(new FormationHistory(player, user, 1, true));
        formationHistoryList.add(new FormationHistory(player1, user, 1, false));
        List<PointHistory> pointHistoryList = new ArrayList<>();
        pointHistoryList.add(new PointHistory(player, 1, 2));
        pointHistoryList.add(new PointHistory(player1, 1, 4));
        when(formationHistoryRepo.findByUserAndWeekNum(anyString(), anyInt())).thenReturn(formationHistoryList);
        when(pointHistoryService.GetPlayersPointInNthWeek(anyList(), anyInt())).thenReturn(pointHistoryList);
        when(formationStatusHistoryRepo.findByUserAndWeekNum(anyString(), anyInt())).thenReturn(new FormationStatusHistory(user, 1, player, player1, true, true));
        FormationDTO formationDTO = formationHistoryService.getFormationHistoryByUserAndWeek("abdo", 1);
        assertEquals(formationDTO.getPlayers().get(0).getPlayer().getPlayer().getName(), "amin");
        assertEquals(formationDTO.getPlayers().get(1).getPlayer().getPlayer().getName(), "amr");
        assertEquals(4, formationDTO.getPlayers().get(0).getPoints());
        assertEquals(4, formationDTO.getPlayers().get(1).getPoints());
        assertEquals(formationDTO.getCaptain().getName(), "amin");
        assertEquals(formationDTO.getViceCaptain().getName(), "amr");
        assertEquals(4, formationDTO.getTotalPoints());
        assertTrue(formationDTO.isBenchBoost());
        assertTrue(formationDTO.isTripleCaptain());
        assertEquals(formationDTO.getPlayers().get(0).getPlayer().getPlayer().getTeam().getName(), "Liverpool");
        assertEquals(formationDTO.getPlayers().get(1).getPlayer().getPlayer().getTeam().getName(), "Arsenal");
        assertTrue(formationDTO.getPlayers().get(0).getPlayer().isStarter());
        assertFalse(formationDTO.getPlayers().get(1).getPlayer().isStarter());
    }

    @Test
    public void testSaveFormationHistory() {
        // Create test data
        List<User> users = new ArrayList<>();
        User user = generateUser("mohamed@mail.com", "mohamed1", "Egypt", "mohamed", "elzainy", "123456");
        users.add(user);

        Player player = new Player("Mohamed Salah", "GK", 10, 1);

        List<CurrentFormation> currentFormations = new ArrayList<>();
        CurrentFormation currentFormation = new CurrentFormation(player, user, true);
        currentFormations.add(currentFormation);

        // Mock repository behavior
        when(userRepository.findAll()).thenReturn(users);
        when(currentFormationRepo.findByUser(user.getUserName())).thenReturn(currentFormations);

        // Perform the method invocation
        formationHistoryService.SaveFormationHistory(1);

        // Verify that the save method was called for each formation
        verify(formationHistoryRepo, times(1)).save(any(FormationHistory.class));

        // Verify that the user repository save method was called
        verify(userRepository, times(1)).save(user);

        // Additional assertions
        ArgumentCaptor<FormationHistory> formationHistoryCaptor = ArgumentCaptor.forClass(FormationHistory.class);
        verify(formationHistoryRepo).save(formationHistoryCaptor.capture());
        FormationHistory savedFormationHistory = formationHistoryCaptor.getValue();

        assertNotNull(savedFormationHistory);
        assertEquals(player, savedFormationHistory.getPlayer());
        assertEquals(user, savedFormationHistory.getUser());
        assertEquals(1, savedFormationHistory.getWeekNum());
        assertTrue(savedFormationHistory.isStarter());
    }
}
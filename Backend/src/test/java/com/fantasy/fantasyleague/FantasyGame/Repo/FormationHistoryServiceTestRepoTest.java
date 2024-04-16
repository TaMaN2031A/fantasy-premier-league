package com.fantasy.fantasyleague.FantasyGame.Repo;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Position;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.FormationHistory;
import com.fantasy.fantasyleague.fantasyGame.Repository.FormationHistoryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static com.fantasy.fantasyleague.Registiration.SharedServices.SharedServices.generateUser;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FormationHistoryServiceTestRepoTest {
    @Autowired
    private FormationHistoryRepo formationHistoryRepo;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void InsertTest() {
        Team team = new Team("Liverpool");
        Player player = new Player("Mohamed Salah", Position.GK.name(),10,1);
        teamRepository.save(team);
        playerRepository.save(player);
        // user sign in using username
        User user = generateUser("madyelzainy@gmail.com", "madyelzainy", "Egypt", "mady", "elzainy", "123456");
        userRepository.save(user);
        List<FormationHistory>formationHistories = new ArrayList<>();
        FormationHistory formationHistory = new FormationHistory(player,user,0,true);
        FormationHistory formationHistory1 = new FormationHistory(player,user,0,true);
        formationHistories.add(formationHistory);
        formationHistories.add(formationHistory1);
        formationHistoryRepo.saveAll(formationHistories);
        FormationHistory formationHistory2 = formationHistoryRepo.findByPlayerAndUserAndWeekNum(player.getID(),user.getUserName(),0);
        assertEquals(0,formationHistory2.getWeekNum());
        assertEquals(player,formationHistory2.getPlayer());
        assertEquals(user,formationHistory2.getUser());
        assertTrue(formationHistory2.isStarter());
    }

    @Test
    public void GetTest() {
        Team team = new Team("Liverpool");
        Player player = new Player("Mohamed Salah", Position.GK.name(),10,1);
        teamRepository.save(team);
        playerRepository.save(player);
        // user sign in using username
        User user = generateUser("madyelzainy@gmail.com", "madyelzainy", "Egypt", "mady", "elzainy", "123456");
        userRepository.save(user);
        FormationHistory formationHistory = new FormationHistory(player,user,0,true);
        formationHistoryRepo.save(formationHistory);
        FormationHistory formationHistory1 = formationHistoryRepo.findByPlayerAndUserAndWeekNum(player.getID(),user.getUserName(),0);
        assertEquals(0,formationHistory1.getWeekNum());
        assertEquals(user,formationHistory1.getUser());
        assertTrue(formationHistory1.isStarter());
    }

    @Test
    public void SaveAllTestAndGetAllForaCertainUser() {
        Team team = new Team("Liverpool");
        Player player = new Player("Mohamed Salah", Position.GK.name(),10,1);
        Player player1 = new Player("abdo", Position.FWD.name(),11,1);
        Player player2 = new Player("abdo", Position.FWD.name(),11,1);
        teamRepository.save(team);
        playerRepository.save(player);
        playerRepository.save(player1);
        playerRepository.save(player2);
        // user sign in using username
        User user = generateUser("madyelzainy@gmail.com", "madyelzainy", "Egypt", "mady", "elzainy", "123456");
        User user1 = generateUser("madyelzain@gmail.com", "madyelzainy1", "Egypt", "mady", "elzainy", "123456");
        userRepository.save(user);
        userRepository.save(user1);
        List<FormationHistory> formationHistories = new ArrayList<>();
        FormationHistory formationHistory = new FormationHistory(player,user,0,true);
        FormationHistory formationHistory1 = new FormationHistory(player1,user,0,true);
        FormationHistory formationHistory2 = new FormationHistory(player2,user1,0,true);
        formationHistories.add(formationHistory);
        formationHistories.add(formationHistory1);
        formationHistories.add(formationHistory2);
        formationHistoryRepo.saveAll(formationHistories);
        List<FormationHistory> formationHistories1 = formationHistoryRepo.findByUserAndWeekNum(user.getUserName(),0);
        List<FormationHistory> formationHistories2 = formationHistoryRepo.findByUserAndWeekNum(user1.getUserName(),0);
        assertEquals(2,formationHistories1.size());
        assertEquals(1,formationHistories2.size());
        for (FormationHistory history : formationHistories1) {
            assertEquals(user, history.getUser());
            assertEquals(0, history.getWeekNum());
            assertTrue(history.isStarter());
        }
    }
}

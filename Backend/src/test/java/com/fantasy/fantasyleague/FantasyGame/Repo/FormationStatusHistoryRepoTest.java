package com.fantasy.fantasyleague.FantasyGame.Repo;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.fantasyGame.Model.FormationStatusHistory.FormationStatusHistory;
import com.fantasy.fantasyleague.fantasyGame.Repository.FormationStatusHistoryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.fantasy.fantasyleague.Registiration.SharedServices.SharedServices.generateUser;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FormationStatusHistoryRepoTest {
    @Autowired
    private FormationStatusHistoryRepo formationStatusHistoryRepo;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertTest() {
        Team team = new Team("Liverpool");
        Player captain = new Player("Mohamed Salah", "GK", 10, 1);
        Player viceCaptain = new Player("Sadio Mane", "MF", 11, 2);
        teamRepository.save(team);
        playerRepository.save(captain);
        playerRepository.save(viceCaptain);

        // User sign in using username
        User user = generateUser("madyelzainy@gmail.com", "madyelzainy", "Egypt", "mady", "elzainy", "123456");
        userRepository.save(user);

        FormationStatusHistory statusHistory = new FormationStatusHistory(user, 0, captain, viceCaptain, true, false);
        formationStatusHistoryRepo.save(statusHistory);

        FormationStatusHistory savedHistory = formationStatusHistoryRepo.findByUserAndWeekNum(user.getUserName(), 0);
        assertEquals(user, savedHistory.getUser());
        assertEquals(0, savedHistory.getWeek_no());
        assertEquals(captain, savedHistory.getCaptain());
        assertEquals(viceCaptain, savedHistory.getViceCaptain());
        assertTrue(savedHistory.isBenchBoost());
        assertFalse(savedHistory.isTripleCaptain());
    }

    @Test
    public void getTest() {
        Team team = new Team("Liverpool");
        Player captain = new Player("Mohamed Salah", "GK", 10, 1);
        Player viceCaptain = new Player("Sadio Mane", "MF", 11, 2);
        teamRepository.save(team);
        playerRepository.save(captain);
        playerRepository.save(viceCaptain);

        // User sign in using username
        User user = generateUser("madyelzainy@gmail.com", "madyelzainy", "Egypt", "mady", "elzainy", "123456");
        userRepository.save(user);

        FormationStatusHistory statusHistory = new FormationStatusHistory(user, 0, captain, viceCaptain, true, false);
        formationStatusHistoryRepo.save(statusHistory);

        FormationStatusHistory retrievedHistory = formationStatusHistoryRepo.findByUserAndWeekNum(user.getUserName(), 0);
        assertEquals(user, retrievedHistory.getUser());
        assertEquals(0, retrievedHistory.getWeek_no());
        assertEquals(captain, retrievedHistory.getCaptain());
        assertEquals(viceCaptain, retrievedHistory.getViceCaptain());
        assertTrue(retrievedHistory.isBenchBoost());
        assertFalse(retrievedHistory.isTripleCaptain());
    }
}

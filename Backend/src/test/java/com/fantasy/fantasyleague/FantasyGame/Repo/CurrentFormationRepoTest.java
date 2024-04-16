package com.fantasy.fantasyleague.FantasyGame.Repo;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Position;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.fantasyGame.Model.Formation.CurrentFormation;
import com.fantasy.fantasyleague.fantasyGame.Repository.CurrentFormationRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static com.fantasy.fantasyleague.Registiration.SharedServices.SharedServices.generateUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CurrentFormationRepoTest {
    @Autowired
    private CurrentFormationRepo currentFormationRepo;

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
        List<CurrentFormation> formationHistories = new ArrayList<>();
        CurrentFormation formationHistory = new CurrentFormation(player,user,true);
        CurrentFormation formationHistory1 = new CurrentFormation(player,user,true);
        formationHistories.add(formationHistory);
        formationHistories.add(formationHistory1);
        currentFormationRepo.saveAll(formationHistories);
        CurrentFormation formationHistory2 = currentFormationRepo.findByUser(user.getUserName()).get(0);
        assertEquals(user,formationHistory2.getUser());
        assertEquals(player,formationHistory2.getPlayer());
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
        CurrentFormation formationHistory = new CurrentFormation(player,user,true);
        currentFormationRepo.save(formationHistory);
        CurrentFormation formationHistory1 = currentFormationRepo.findByUser(user.getUserName()).get(0);
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
        List<CurrentFormation> formationHistories = new ArrayList<>();
        CurrentFormation formationHistory = new CurrentFormation(player,user,true);
        CurrentFormation formationHistory1 = new CurrentFormation(player1,user,true);
        CurrentFormation formationHistory2 = new CurrentFormation(player2,user1,true);
        formationHistories.add(formationHistory);
        formationHistories.add(formationHistory1);
        formationHistories.add(formationHistory2);
        currentFormationRepo.saveAll(formationHistories);
        List<CurrentFormation> formationHistories1 = currentFormationRepo.findByUser(user.getUserName());
        List<CurrentFormation> formationHistories2 = currentFormationRepo.findByUser(user1.getUserName());
        assertEquals(2,formationHistories1.size());
        assertEquals(1,formationHistories2.size());
        for (CurrentFormation history : formationHistories1) {
            assertEquals(user, history.getUser());
            assertTrue(history.isStarter());
        }
    }
}

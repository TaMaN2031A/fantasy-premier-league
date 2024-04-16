package com.fantasy.fantasyleague.FantasyGame.Repo;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Position;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.Model.Role;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.FormationHistory;
import com.fantasy.fantasyleague.fantasyGame.Repository.CurrentFormationRepo;
import com.fantasy.fantasyleague.fantasyGame.Repository.FormationHistoryRepo;
import com.fantasy.fantasyleague.fantasyGame.Repository.FormationStatusHistoryRepo;
import com.fantasy.fantasyleague.fantasyGame.Repository.PointHistoryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserTeamRepoTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FormationHistoryRepo formationHistoryRepo;


    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void InsertTest() {
        Team team = new Team("Arsenal");
        Team team1 = new Team("Liverpool");
        teamRepository.save(team);
        teamRepository.save(team1);
        SignInDTO signin = new SignInDTO();
        User signup = new User();
        signin.setUserNameOrEmail("mohamed_arous1");
        signin.setPassword("12259861");
        signin.setRole(Role.USER);
        signup.setFirstName("mohamed1");
        signup.setLastName("arous1");
        signup.setRegion("egypt");
        signup.setEmail("mohamed.arous9401@gmail.com");
        signup.setPassword("12259861");
        signup.setUserName("mohamed_arous1");
        signup.setBenchBoost(true);
        signup.setTripleCaptain(true);
        signup.setMoneyRemaining(100.0);
        userRepository.save(signup);
        User user1 = userRepository.findByEmailOrUserName("mohamed_arous1","mohamed_arous1");
        Player player1 = new Player("player1", Position.FWD.name(),1,1);
        Player player2 = new Player("player2",Position.FWD.name(),2,1);
        playerRepository.save(player1);
        playerRepository.save(player2);
        signup.setCaptain(player1);
        signup.setViceCaptain(player2);
        userRepository.save(user1);
        User user2 = userRepository.findByEmailOrUserName("mohamed_arous1","mohamed_arous1");
        assertTrue(user2.getBenchBoost());
        assertTrue(user2.getTripleCaptain());
        assertEquals(signup.getUserName(),"mohamed_arous1");
        assertEquals(signup.getCaptain(),player1);
        assertEquals(signup.getViceCaptain(),player2);
    }
}

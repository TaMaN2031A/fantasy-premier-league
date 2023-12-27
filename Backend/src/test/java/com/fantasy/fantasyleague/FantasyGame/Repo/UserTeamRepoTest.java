package com.fantasy.fantasyleague.FantasyGame.Repo;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import com.fantasy.fantasyleague.Registiration.DTO.SignInDTO;
import com.fantasy.fantasyleague.Registiration.Model.Role;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.Registiration.Service.RegistrationService;
import com.fantasy.fantasyleague.fantasyGame.Model.Formation.CurrentFormation;
import com.fantasy.fantasyleague.fantasyGame.Model.FormationStatusHistory.FormationStatusHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.Formation;
import com.fantasy.fantasyleague.fantasyGame.Repository.PointHistoryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserTeamRepoTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PointHistoryRepo pointHistoryRepo;

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
        signup.setEmail("mohamed.arous9401@gmail.com");
        signup.setPassword("12259861");
        signup.setUserName("mohamed_arous1");
        signup.setBenchBoost(false);
        signup.setTripleCaptain(false);
        signup.setMoneyRemaining(100.0);
        ArrayList<Formation>formations = new ArrayList<>();
        ArrayList<CurrentFormation>currentFormations = new ArrayList<>();
        ArrayList<FormationStatusHistory> formationStatusHistories = new ArrayList<>();
        for(int i = 0;i<11;i++){
            Player player = new Player("player"+i,"ST",i,1);
            playerRepository.save(player);
            Formation formation = new Formation(player,signup,1,false);
            formations.add(formation);
//            formationRepository.save(formation)
            CurrentFormation currentFormation = new CurrentFormation(player,signup,false);
            currentFormations.add(currentFormation);
//            formationRepository.save(currentFormation);
        }
        Player player1 = new Player("player1","ST",1,1);
        Player player2 = new Player("player2","ST",2,1);
        FormationStatusHistory formationStatusHistory = new FormationStatusHistory(signup,0,player1,player2,false,false);
//        formationStatusHistoryRepository.save(formationStatusHistory);
        formationStatusHistories.add(formationStatusHistory);
        signup.setFormations(formations);
        signup.setCurrentFormations(currentFormations);
        signup.setFormationStatusHistory(formationStatusHistories);
        userRepository.save(signup);
//        userRepository.findByEmailOrUserName();
    }
}

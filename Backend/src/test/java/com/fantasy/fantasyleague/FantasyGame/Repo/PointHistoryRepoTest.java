package com.fantasy.fantasyleague.FantasyGame.Repo;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import com.fantasy.fantasyleague.fantasyGame.Model.PointHistory.PointHistory;
import com.fantasy.fantasyleague.fantasyGame.Repository.PointHistoryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PointHistoryRepoTest {
    @Autowired
    private PointHistoryRepo pointHistoryRepo;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void InsertTest() {
        Team team = new Team("ahmed");
        Player player = new Player("abdo", "ST", 1, 1);

        teamRepository.save(team);
        playerRepository.save(player);

        PointHistory pointHistory = new PointHistory(player, 0 , 1);
        pointHistoryRepo.save(pointHistory);

        PointHistory pointHistory1 = pointHistoryRepo.findAllByWeekNo(0).get(0);

        assertEquals(0,pointHistory1.getPoints());
        assertEquals(player,pointHistory1.getPlayerPoint());
        assertEquals(1,pointHistory1.getPoints());
    }
    @Test
    void GetTest() {
        Team team = new Team("ahmed");
        Player player = new Player("abdo", "ST", 1, 1);

        teamRepository.save(team);
        playerRepository.save(player);

        PointHistory pointHistory = new PointHistory(player, 0 , 1);

        pointHistoryRepo.save(pointHistory);
        pointHistoryRepo.findByPlayerPointAndWeekNo(1,0);
    }

}

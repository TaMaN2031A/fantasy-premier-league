package com.fantasy.fantasyleague.RealLeague.Repository;


import com.fantasy.fantasyleague.RealLeague.Model.PlayedMatch;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.PlayerStatistics;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Repository.MatchRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerStatisticsRepoository;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@TestPropertySource("classpath:test_local.properties")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class playedMatchRepoTest {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerStatisticsRepoository playerStatisticsRepoository;


    @Order(1)
    @Test
    void playedMatch_InsertMatchAndStatistics(){
        Team team1 = new Team( "liverpool");
        Team team2 = new Team( "arsenal");
        Player player1 = new Player("mohamed arous" , "fwd" , 15 , 1);
        Player player2 = new Player("Amr Ahmed" , "fwd" , 10 , 2);
        Date date = new Date() ;
        teamRepository.save(team1);
        teamRepository.save(team2);
        playerRepository.save(player1);
        playerRepository.save(player2);
        PlayedMatch  match= PlayedMatch.builder()
                .away(team1)
                .home(team2)
                .date(date)
                .redCardsAway(1)
                .redCardsHome(2)
                .yellowCardsAway(5)
                .yellowCardsHome(6)
                .week(1)
                .stadium("camp nou")
                .build();
        PlayerStatistics playerStatistics1 = new PlayerStatistics().builder()
                .player(player1)
                .assists(1)
                .man_of_match(true)
                .goal(5)
                .effectivness(80)
                .redCards(0)
                .saves(0)
                .yellowCards(4)
                .match(match)
                .build();

        PlayerStatistics playerStatistics2 = new PlayerStatistics().builder()
                .player(player2)
                .assists(2)
                .man_of_match(false)
                .goal(44)
                .effectivness(99)
                .redCards(5)
                .saves(4)
                .yellowCards(4)
                .match(match)
                .build();
        matchRepository.save(match);
        playerStatisticsRepoository.save(playerStatistics1);
        playerStatisticsRepoository.save(playerStatistics2);
        Team liverbool = teamRepository.findByName("liverpool");
        Team arsenal = teamRepository.findByName("arsenal");
        PlayedMatch match3 = matchRepository.findByHomeAndAway(arsenal, liverbool);
        assertEquals(playerStatisticsRepoository.findAllByMatch(match3).size() , 2 );


    }

    @Order(2)
    @Test
    void playedMatch_GetbyWeek(){
        Team liverbool = teamRepository.findByName("liverpool");
        Team arsenal = teamRepository.findByName("arsenal");
        Team man_city = new Team( "man city");
        Team man_united = new Team( "man united");
        teamRepository.save(man_city);
        teamRepository.save(man_united);
        PlayedMatch  match= PlayedMatch.builder()
                .away(liverbool)
                .home(man_city)
                .date(new Date())
                .redCardsAway(1)
                .redCardsHome(2)
                .yellowCardsAway(5)
                .yellowCardsHome(6)
                .week(2)
                .stadium("camp nou")
                .build();
        PlayedMatch  match2= PlayedMatch.builder()
                .away(man_united)
                .home(arsenal)
                .date(new Date())
                .redCardsAway(1)
                .redCardsHome(2)
                .yellowCardsAway(5)
                .yellowCardsHome(6)
                .week(2)
                .stadium("camp nou")
                .build();
        matchRepository.save(match);
        matchRepository.save(match2);
        assertEquals(matchRepository.findByWeek(1).size() , 1);
        assertEquals(matchRepository.findByWeek(2).size() , 2);
    }

    @Order(3)
    @Test
    void playedMatch_DeletePlayer(){
        playerRepository.deleteById(playerRepository.findByName("mohamed arous").getID());
        Team t = teamRepository.findByName("liverpool");
        Team t2 = teamRepository.findByName("arsenal");
        PlayedMatch match = matchRepository.findByHomeAndAway(t2, t);
        List<PlayerStatistics> y = playerStatisticsRepoository.findAllByMatch(match);
        assertEquals(y.size() , 1 );
    }


    @Order(4)
    @Test
    void playedMatch_DeleteMatch(){
        playerRepository.deleteById(playerRepository.findByName("Amr Ahmed").getID());
        Team t = teamRepository.findByName("liverpool");
        Team t2 = teamRepository.findByName("arsenal");
        PlayedMatch match = matchRepository.findByHomeAndAway(t2, t);
        matchRepository.delete(match);
        List<PlayerStatistics> y = playerStatisticsRepoository.findAllByMatch(match);
        assertEquals(y.size() , 0 );

    }

}

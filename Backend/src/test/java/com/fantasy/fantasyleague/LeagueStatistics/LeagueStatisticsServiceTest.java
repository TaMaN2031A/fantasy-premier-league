package com.fantasy.fantasyleague.LeagueStatistics;

import com.fantasy.fantasyleague.RealLeague.DTO.TopPlayer;
import com.fantasy.fantasyleague.RealLeague.Model.PlayedMatch;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Repository.MatchRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import com.fantasy.fantasyleague.RealLeague.Service.LeagueStatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class LeagueStatisticsServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    private LeagueStatisticsService leagueStatisticsService;

    @Test
    public void testGetTop() {
        MockitoAnnotations.openMocks(this);

        // Sample players for testing
        Player player1 = new Player();
                player1.setName("Alice");
        Player player2 = new Player();
                player2.setName("John");

        // Mock the playerRepository to return the sample players
        when(playerRepository.findAll()).thenReturn(Arrays.asList(player1, player2));

        // Define a criteria function (e.g., based on player points)
        Function<Player, Integer> pointsCriteria = Player::getGoals;

        // Define a mapper function
        Function<Player, TopPlayer> topPlayerMapper = player -> new TopPlayer(player.getName(), player.getGoals());

        // Call the method to be tested
        List<TopPlayer> topPlayers = leagueStatisticsService.getTop(pointsCriteria, topPlayerMapper);

        // Assert the expected result based on the sample players and criteria
        assertEquals(2, topPlayers.size());
        assertEquals("Alice", topPlayers.get(0).getName());
        assertEquals("John", topPlayers.get(1).getName());
        // Add more assertions based on your criteria and expected results
    }



    @Test
    public void sortTeamTestAccordingPoints() {
        // Create some teams for testing
        Team team1 = new Team();
        team1.setName("Team1");
        team1.setPoints(10);
        team1.setGoals_for(20);
        team1.setGoals_conceded(15);

        Team team2 = new Team();
        team2.setName("Team2");
        team2.setPoints(15);
        team2.setGoals_for(25);
        team2.setGoals_conceded(10);

        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        assertEquals(15, leagueStatisticsService.sortTeams(teams).get(0).getPoints());
    }

    @Test
    public void sortTeamTestAccordingGoalDifference() {
        // Create some teams for testing
        Team team1 = new Team();
        team1.setName("Team1");
        team1.setPoints(15);
        team1.setGoals_for(20);
        team1.setGoals_conceded(15);
        /*difference is 5*/

        Team team2 = new Team();
        team2.setName("Team2");
        team2.setPoints(15);
        team2.setGoals_for(25);
        team2.setGoals_conceded(10);
        /*difference is 15*/
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        assertEquals(25, leagueStatisticsService.sortTeams(teams).get(0).getGoals_for());
    }

    @Test
    public void sortTeamTestAccordingNumberGoalsScored() {
        // Create some teams for testing
        Team team1 = new Team();
        team1.setName("Team1");
        team1.setPoints(15);
        team1.setGoals_for(20);
        team1.setGoals_conceded(15);

        Team team2 = new Team();
        team2.setName("Team2");
        team2.setPoints(15);
        team2.setGoals_for(23);
        team2.setGoals_conceded(18);

        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        assertEquals(23, leagueStatisticsService.sortTeams(teams).get(0).getGoals_for());
    }

    @Test
    public void sortTeamTestAccordingMostPointsInHeadToHead() {
        // Create some teams for testing
        Team team1 = new Team();
        team1.setName("Team1");
        team1.setPoints(15);
        team1.setGoals_for(20);
        team1.setGoals_conceded(15);

        Team team2 = new Team();
        team2.setName("Team2");
        team2.setPoints(15);
        team2.setGoals_for(20);
        team2.setGoals_conceded(15);

        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        PlayedMatch firstMatch = PlayedMatch.builder()
                .home(team1)
                .away(team2)
                .homeGoals(3)
                .awayGoals(3)
                .build();
        PlayedMatch secondMatch = PlayedMatch.builder()
                .home(team2)
                .away(team1)
                .homeGoals(2)
                .awayGoals(1)
                .build();


        when(matchRepository.findByHomeAndAway(team1, team2)).thenReturn(firstMatch);
        when(matchRepository.findByHomeAndAway(team2, team1)).thenReturn(secondMatch);

        assertEquals("Team2", leagueStatisticsService.sortTeams(teams).get(0).getName());
    }

    @Test
    public void sortTeamTestAccordingMostAwayHeadToHead() {
        // Create some teams for testing
        Team team1 = new Team();
        team1.setName("Team1");
        team1.setPoints(15);
        team1.setGoals_for(20);
        team1.setGoals_conceded(15);

        Team team2 = new Team();
        team2.setName("Team2");
        team2.setPoints(15);
        team2.setGoals_for(20);
        team2.setGoals_conceded(15);

        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        PlayedMatch firstMatch = PlayedMatch.builder()
                .home(team1)
                .away(team2)
                .homeGoals(3)
                .awayGoals(3)
                .build();
        PlayedMatch secondMatch = PlayedMatch.builder()
                .home(team2)
                .away(team1)
                .homeGoals(4)
                .awayGoals(4)
                .build();


        when(matchRepository.findByHomeAndAway(team1, team2)).thenReturn(firstMatch);
        when(matchRepository.findByHomeAndAway(team2, team1)).thenReturn(secondMatch);

        assertEquals("Team1", leagueStatisticsService.sortTeams(teams).get(0).getName());
    }

    @Test
    public void getPointsOfMatchesTest() {
        // Create some teams for testing
        Team team1 = new Team();
        team1.setName("Team1");
        team1.setPoints(10);
        team1.setGoals_for(20);
        team1.setGoals_conceded(15);

        Team team2 = new Team();
        team2.setName("Team2");
        team2.setPoints(15);
        team2.setGoals_for(25);
        team2.setGoals_conceded(10);

        // Create some matches for testing
        PlayedMatch firstMatch = PlayedMatch.builder()
                .home(team1)
                .away(team2)
                .homeGoals(2)
                .awayGoals(3)
                .build();

        int[] arr0 = (leagueStatisticsService.getPointsOfMatch(null));
        assertEquals(0, arr0[0]);
        assertEquals(0, arr0[1]);

        int[] arr1 = leagueStatisticsService.getPointsOfMatch(firstMatch);
        // home team lost
        assertEquals(0, arr1[0]);
        // away team won
        assertEquals(3, arr1[1]);

        /*------------------------------------------------------------------------------*/

        PlayedMatch secondMatch = PlayedMatch.builder()
                .home(team2)
                .away(team1)
                .homeGoals(1)
                .awayGoals(1)
                .build();

        int[] arr2 = leagueStatisticsService.getPointsOfMatch(firstMatch);
        // home team lost
        assertEquals(0, arr2[0]);
        // away team won
        assertEquals(3, arr2[1]);

    }


}

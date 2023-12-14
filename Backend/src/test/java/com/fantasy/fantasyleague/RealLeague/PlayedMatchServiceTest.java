package com.fantasy.fantasyleague.RealLeague;
//
//import com.fantasy.fantasyleague.RealLeague.DTO.MatchStatisticsDTO;
//import com.fantasy.fantasyleague.RealLeague.Model.Player;
//import com.fantasy.fantasyleague.RealLeague.Model.PlayerStatistics;
//import com.fantasy.fantasyleague.RealLeague.Model.Team;
//import com.fantasy.fantasyleague.RealLeague.Model.UpcomingMatch;
//import com.fantasy.fantasyleague.RealLeague.Repository.*;
//import com.fantasy.fantasyleague.RealLeague.Service.MatchServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class PlayedMatchServiceTest {
//
//    @Mock
//    private MatchRepository matchRepository;
//
//    @Mock
//    private PlayerStatisticsRepoository playerStatisticsRepoository;
//
//    @Mock
//    private TeamRepository teamRepository;
//
//    @Mock
//    private PlayerRepository playerRepository;
//
//    @Mock
//    private UpcomingMatchRepository upcomingMatchRepository;
//
//    @InjectMocks
//    private MatchServiceImpl matchService;
//
//    @Test
//    void PlayedMatchServiceTest1(){
//        Team team1 = new Team( "liverpool");
//        MatchStatisticsDTO matchStatisticsDTO = MatchStatisticsDTO.builder()
//                .away("liverpool")
//                .home("man city")
//                .build();
//        when(teamRepository.findByName(anyString())).thenAnswer(invocation -> {
//            String inputParameter = (String) invocation.getArguments()[0];
//
//            // Perform classification based on the input parameter
//            if (inputParameter.equals("liverpool") ) {
//                return team1;
//            } else {
//                return null;
//            }
//
//        });
//        String answer = matchService.addMatchStatiscis(matchStatisticsDTO);
//        Assertions.assertEquals(answer , "Teams not in the league.");
//
//    }
//
//
//
//    @Test
//    void PlayedMatchServiceTest2(){
//        Team team1 = new Team( "liverpool");
//        Team team2 = new Team( "arsenal");
//        MatchStatisticsDTO matchStatisticsDTO = MatchStatisticsDTO.builder()
//                .away("liverpool")
//                .home("arsenal")
//                .build();
//        when(teamRepository.findByName(anyString())).thenAnswer(invocation -> {
//            String inputParameter = (String) invocation.getArguments()[0];
//
//            // Perform classification based on the input parameter
//
//            if (inputParameter.equals("liverpool") ) {
//                return team1;
//            } else {
//                return team2;
//            }
//
//        });
//
//        when(upcomingMatchRepository.findByHomeAndAway(any(Team.class) , any(Team.class))).thenReturn(null);
//        String answer = matchService.addMatchStatiscis(matchStatisticsDTO);
//        Assertions.assertEquals(answer , "Match is not in this week.");
//    }
//
//
//    @Test
//    void PlayedMatchServiceTest3(){
//        Team team1 = new Team( "liverpool");
//        Team team2 = new Team( "arsenal");
//        List<String> x= new ArrayList<>();
//        x.add("mohamed arous");
//        x.add("amr ahmed");
//        x.add("amr ahmed1");
//        x.add("amr ahmed2");
//        x.add("amr ahmed3");
//        x.add("amr ahmed3");
//        x.add("amr ahmed4");
//        x.add("amr ahmed5");
//        x.add("amr ahmed6");
//        x.add("amr ahmed7");
//        x.add("amr ahmed8");
//        x.add("amr ahmed9");
//        x.add("amr ahmed10");
//        x.add("amr ahmed11");
//
//
//        MatchStatisticsDTO matchStatisticsDTO = MatchStatisticsDTO.builder()
//                .away("liverpool")
//                .home("man city")
//                .awayPlayersAssist(List.copyOf(x))
//                .awayPlayersPlayed(List.copyOf(x))
//                .awayPlayersRedCards(List.copyOf(x))
//                .awayPlayersSaves(List.copyOf(x))
//                .awayPlayersScore(List.copyOf(x))
//                .awayPlayersYellowCards(List.copyOf(x))
//                .homePlayersPlayed(List.copyOf(x))
//                .build();
//        when(teamRepository.findByName(anyString())).thenAnswer(invocation -> {
//            String inputParameter = (String) invocation.getArguments()[0];
//
//            // Perform classification based on the input parameter
//
//            if (inputParameter.equals("liverpool") ) {
//                return team1;
//            } else {
//                return team2;
//            }
//
//        });
//        team1.setID(1);
//        team2.setID(2);
//        UpcomingMatch upcomingMatch = UpcomingMatch.builder()
//                .away(team1)
//                .home(team2)
//                .week(0)
//                .stadium("camp nou")
//                .ID(1)
//                .build();
//        when(upcomingMatchRepository.findByHomeAndAway(any(Team.class) , any(Team.class))).thenReturn(upcomingMatch);
//        Player player1 = new Player("mohamed arous" , "fwd" , 15 , 1);
//        Player player2 = new Player("amr ahmed" , "fwd" , 15 , 1);
//        when(playerRepository.findByName(anyString())).thenAnswer(invocation -> {
//            String inputParameter = (String) invocation.getArguments()[0];
//
//            // Perform classification based on the input parameter
//
//            if (inputParameter.equals("mohamed arous") ) {
//                return player1;
//            } else {
//                return player2;
//            }
//
//        });
//        PlayerStatistics playerStatistics1 = PlayerStatistics
//                .builder()
//                .goal(1)
//                .man_of_match(true)
//                .effectivness(99)
//                .build();
//        when(playerStatisticsRepoository.save(any(PlayerStatistics.class))).thenReturn(playerStatistics1);
//
//        String answer = matchService.addMatchStatiscis(matchStatisticsDTO);
//        Assertions.assertEquals(answer , "Match is added successfully");
//    }
//
//}


//package com.fantasy.fantasyleague.LeagueStatistics;

import com.fantasy.fantasyleague.RealLeague.Model.PlayedMatch;
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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class PlayedMatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    private LeagueStatisticsService leagueStatisticsService;

    /*
     * needed variables;
     * */
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

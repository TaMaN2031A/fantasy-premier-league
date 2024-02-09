package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.DTO.MatchStatisticsDTO;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.PlayerStatistics;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Model.UpcomingMatch;
import com.fantasy.fantasyleague.RealLeague.Repository.*;
import com.fantasy.fantasyleague.RealLeague.Service.MatchServiceImpl;
import com.fantasy.fantasyleague.fantasyGame.Service.PointsCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayedMatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private PlayerStatisticsRepoository playerStatisticsRepoository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private UpcomingMatchRepository upcomingMatchRepository;

    @Mock
    private PointsCalculator pointsCalculator;

    @InjectMocks
    private MatchServiceImpl matchService;

    @Test
    void PlayedMatchServiceTest1(){
        Team team1 = new Team( "liverpool");
        MatchStatisticsDTO matchStatisticsDTO = MatchStatisticsDTO.builder()
                .away("liverpool")
                .home("man city")
                .build();
        when(teamRepository.findByName(anyString())).thenAnswer(invocation -> {
            String inputParameter = (String) invocation.getArguments()[0];

            // Perform classification based on the input parameter
            if (inputParameter.equals("liverpool") ) {
                return team1;
            } else {
                return null;
            }

        });
        String answer = matchService.addMatchStatiscis(matchStatisticsDTO);
        Assertions.assertEquals(answer , "Teams not in the league.");

    }



    @Test
    void PlayedMatchServiceTest2(){
        Team team1 = new Team( "liverpool");
        Team team2 = new Team( "arsenal");
        MatchStatisticsDTO matchStatisticsDTO = MatchStatisticsDTO.builder()
                .away("liverpool")
                .home("arsenal")
                .build();
        when(teamRepository.findByName(anyString())).thenAnswer(invocation -> {
            String inputParameter = (String) invocation.getArguments()[0];

            // Perform classification based on the input parameter

            if (inputParameter.equals("liverpool") ) {
                return team1;
            } else {
                return team2;
            }

        });

        when(upcomingMatchRepository.findByHomeAndAway(any(Team.class) , any(Team.class))).thenReturn(null);
        String answer = matchService.addMatchStatiscis(matchStatisticsDTO);
        Assertions.assertEquals(answer , "Match is not in this week.");
    }


    @Test
    void PlayedMatchServiceTest3(){
        Team team1 = new Team( "liverpool");
        Team team2 = new Team( "arsenal");
        List<String> x= new ArrayList<>();
        x.add("mohamed arous");
        x.add("amr ahmed");
        x.add("amr ahmed1");
        x.add("amr ahmed2");
        x.add("amr ahmed3");
        x.add("amr ahmed3");
        x.add("amr ahmed4");
        x.add("amr ahmed5");
        x.add("amr ahmed6");
        x.add("amr ahmed7");
        x.add("amr ahmed8");
        x.add("amr ahmed9");
        x.add("amr ahmed10");
        x.add("amr ahmed11");


        MatchStatisticsDTO matchStatisticsDTO = MatchStatisticsDTO.builder()
                .away("liverpool")
                .home("man city")
                .awayPlayersAssist(List.copyOf(x))
                .awayPlayersPlayed(List.copyOf(x))
                .awayPlayersRedCards(List.copyOf(x))
                .awayPlayersSaves(List.copyOf(x))
                .awayPlayersScore(List.copyOf(x))
                .awayPlayersYellowCards(List.copyOf(x))
                .homePlayersPlayed(List.copyOf(x))
                .build();
        when(teamRepository.findByName(anyString())).thenAnswer(invocation -> {
            String inputParameter = (String) invocation.getArguments()[0];

            // Perform classification based on the input parameter

            if (inputParameter.equals("liverpool") ) {
                return team1;
            } else {
                return team2;
            }

        });
        team1.setID(1);
        team2.setID(2);
        UpcomingMatch upcomingMatch = UpcomingMatch.builder()
                .away(team1)
                .home(team2)
                .week(0)
                .stadium("camp nou")
                .ID(1)
                .build();
        when(upcomingMatchRepository.findByHomeAndAway(any(Team.class) , any(Team.class))).thenReturn(upcomingMatch);
        Player player1 = new Player("mohamed arous" , "fwd" , 15 , 1);
        Player player2 = new Player("amr ahmed" , "fwd" , 15 , 1);
        when(playerRepository.findByName(anyString())).thenAnswer(invocation -> {
            String inputParameter = (String) invocation.getArguments()[0];

            // Perform classification based on the input parameter

            if (inputParameter.equals("mohamed arous") ) {
                return player1;
            } else {
                return player2;
            }

        });
        PlayerStatistics playerStatistics1 = PlayerStatistics
                .builder()
                .goal(1)
                .man_of_match(true)
                .effectivness(99)
                .build();
        when(playerStatisticsRepoository.save(any(PlayerStatistics.class))).thenReturn(playerStatistics1);

        String answer = matchService.addMatchStatiscis(matchStatisticsDTO);
        Assertions.assertEquals(answer , "Match is added successfully");
    }

}

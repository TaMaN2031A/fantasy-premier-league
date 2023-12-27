package com.fantasy.fantasyleague.FantasyGame.Service;

import com.fantasy.fantasyleague.RealLeague.DTO.MatchStatisticsDTO;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.fantasyGame.Model.Lock;
import com.fantasy.fantasyleague.fantasyGame.Model.PointHistory.PointHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.WeekNo;
import com.fantasy.fantasyleague.fantasyGame.Repository.PointHistoryRepo;
import com.fantasy.fantasyleague.fantasyGame.Repository.WeekNoRepo;
import com.fantasy.fantasyleague.fantasyGame.Service.PointsCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PointCalculatorTest {

    @Mock
    private PointHistory pointHistory;

    @Mock
    private PointHistoryRepo pointHistoryRepo;

    @Mock
    private WeekNoRepo weekNoRepo;

    @Mock
    private PlayerRepository playerRepo;

    @InjectMocks
    private PointsCalculator pointsCalculator;

    /**
     * Method under test:
     * {@link PointsCalculator#handleHomePoints(MatchStatisticsDTO)}
     */
    @Test
    void testHandleHomePoints() {
        // Arrange, Act and Assert
        assertTrue(pointsCalculator.handleHomePoints(new MatchStatisticsDTO()).isEmpty());
    }

    /**
     * Method under test:
     * {@link PointsCalculator#handleHomePoints(MatchStatisticsDTO)}
     */
    @Test
    void testHandleHomePoints2() {
        // Arrange
        MatchStatisticsDTO matchData = mock(MatchStatisticsDTO.class);
        when(matchData.getHomePlayersAssist()).thenReturn(new ArrayList<>());
        when(matchData.getHomePlayersPlayed()).thenReturn(new ArrayList<>());
        when(matchData.getHomePlayersRedCards()).thenReturn(new ArrayList<>());
        when(matchData.getHomePlayersSaves()).thenReturn(new ArrayList<>());
        when(matchData.getHomePlayersScore()).thenReturn(new ArrayList<>());
        when(matchData.getHomePlayersYellowCards()).thenReturn(new ArrayList<>());

        // Act
        Map<String, Integer> actualHandleHomePointsResult = pointsCalculator.handleHomePoints(matchData);

        // Assert
        verify(matchData).getHomePlayersAssist();
        verify(matchData).getHomePlayersPlayed();
        verify(matchData).getHomePlayersRedCards();
        verify(matchData).getHomePlayersSaves();
        verify(matchData).getHomePlayersScore();
        verify(matchData).getHomePlayersYellowCards();
        assertTrue(actualHandleHomePointsResult.isEmpty());
    }

    @Test
    void convertToCountMapTest() {
        List<String> scorers = Arrays.asList(
                "Player1", "Player2", "Player1", "Player3", "Player2", "Player1"
        );
        Map<String, Long> map = pointsCalculator.convertToCountMap(scorers);

        // verify that the map contains the correct data in each entry
        assert(map.get("Player1") == 3);
        assert(map.get("Player2") == 2);
        assert(map.get("Player3") == 1);
    }

    @Test
    void storePointsTest() {
        // arrange
        Map<String, Integer> map = Map.of(
                "Player1", 3,
                "Player2", 2,
                "Player3", 1
        );

        Player player1 = new Player("Player1", "GK", 21, 1);
        Player player2 = new Player("Player2", "DF", 22, 1);
        Player player3 = new Player("Player3", "DF", 23, 1);
        when(playerRepo.findByName("Player1")).thenReturn(player1);
        when(playerRepo.findByName("Player2")).thenReturn(player2);
        when(playerRepo.findByName("Player3")).thenReturn(player3);

        // Mock the behavior of the PointHistory constructor
        PointHistory pointHistoryP1 = new PointHistory(player1, 1, 3);
        PointHistory pointHistoryP2 = new PointHistory(player2, 1, 2);
        PointHistory pointHistoryP3 = new PointHistory(player3, 1, 1);
        when(pointHistoryRepo.save(any(PointHistory.class))).thenAnswer(invocation -> {
            String playerName = ((PointHistory) invocation.getArguments()[0]).getPlayer().getName();

            // Perform classification based on the input parameter
            return switch (playerName) {
                case "Player1" -> pointHistoryP1;
                case "Player2" -> pointHistoryP2;
                case "Player3" -> pointHistoryP3;
                default -> null;
            };
        });

        // act
        List<PointHistory> pointHistoryList = pointsCalculator.storePoints(map, 1);


        // assert
        assert(pointHistoryList.size() == 3);
        Assertions.assertEquals(3, pointHistoryList.get(0).getPoints());
        Assertions.assertEquals(2, pointHistoryList.get(1).getPoints());
        Assertions.assertEquals(1, pointHistoryList.get(2).getPoints());
    }


    @Test
    void executeTest() {

        // arrange
        List<String> list = Arrays.asList(
                "Player1", "Player2", "Player1"
        );
        MatchStatisticsDTO matchData = MatchStatisticsDTO.builder()
                .homePlayersScore(list)
                .homePlayersYellowCards(list)
                .homePlayersAssist(list)
                .homePlayersSaves(list)
                .homePlayersPlayed(list)
                .build();
        when(weekNoRepo.count()).thenReturn(1L);
        when(playerRepo.findByName("Player1")).thenReturn(new Player("Player1", "GK", 21, 1));
        when(playerRepo.findByName("Player2")).thenReturn(new Player("Player2", "DF", 22, 1));
        when(weekNoRepo.findById(Lock.X)).thenReturn(Optional.of(new WeekNo(Lock.X, 1)));

        // act
        pointsCalculator.execute(matchData);

        // assert
        verify(weekNoRepo).count();
    }



    @Test
    void multiplyNullInIntegerTest(){
        Integer a = null;
        Integer b = 2;
        assertThrows(Exception.class, () -> {
            Integer i = a * b;
        });
    }
}

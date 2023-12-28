package com.fantasy.fantasyleague.FantasyGame.Service;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.fantasyGame.Model.PointHistory.PointHistory;
import com.fantasy.fantasyleague.fantasyGame.Repository.PointHistoryRepo;
import com.fantasy.fantasyleague.fantasyGame.Service.PointHistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PointHistoryServiceTest  {
    @InjectMocks
    private PointHistoryService pointHistoryService;

    @Mock
    private PointHistoryRepo pointHistoryRepo;

    @Test
    void testSavePointHistory() {
        // Arrange
        List<PointHistory> pointHistoryList = List.of(new PointHistory(new Player("Mohamed Salah", "GK",10,1),1,5));

        // Act
        pointHistoryService.SavePointHistory(pointHistoryList);

        // Assert
        verify(pointHistoryRepo, times(1)).saveAll(pointHistoryList);
    }

    @Test
    void testSavePointHistoryException() {
        // Arrange
        List<PointHistory> pointHistoryList = List.of(new PointHistory(new Player("Mohamed Salah", "GK",10,1),1,5));
        doThrow(new RuntimeException("Simulated exception")).when(pointHistoryRepo).saveAll(any());

        // Act and Assert
        pointHistoryService.SavePointHistory(pointHistoryList); // It should not throw an exception
    }

    @Test
    void testGetPlayersPointInNthWeek() {
        // Arrange
        int weekNo = 1;
        Player player1 = new Player("Mohamed Salah", "GK",10,1);
        Player player2 = new Player("abdo","MID",11,1);
        player1.setID(1);
        player2.setID(2);
        List<Player> players = Arrays.asList(player1, player2);

        PointHistory pointHistory1 = new PointHistory(player1,weekNo,5);
        PointHistory pointHistory2 = new PointHistory(player2,weekNo,2);

        when(pointHistoryRepo.findByPlayerAndWeekNo(player1.getID(), weekNo)).thenReturn(pointHistory1);
        when(pointHistoryRepo.findByPlayerAndWeekNo(player2.getID(), weekNo)).thenReturn(pointHistory2);

        // Act
        List<PointHistory> result = pointHistoryService.GetPlayersPointInNthWeek(players, weekNo);

        // Assert
        assertEquals(2, result.size());
        assertEquals(pointHistory1, result.get(0));
        assertEquals(pointHistory2, result.get(1));
    }
}

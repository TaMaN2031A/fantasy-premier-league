package com.fantasy.fantasyleague.FantasyGame.Service;

import com.fantasy.fantasyleague.fantasyGame.Model.Lock;
import com.fantasy.fantasyleague.fantasyGame.Model.WeekNo;
import com.fantasy.fantasyleague.fantasyGame.Repository.WeekNoRepo;
import com.fantasy.fantasyleague.fantasyGame.Service.FormationHistoryService;
import com.fantasy.fantasyleague.fantasyGame.Service.StartingWeekService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class StartWeekServiceTest {
    @InjectMocks
    private StartingWeekService startingWeekService;

    @Mock
    private FormationHistoryService formationHistoryService;

    @Mock
    private WeekNoRepo weekNoRepo;


    @Test
    void testStartWeekWeekNoExists() {
        // Arrange
        WeekNo weekNo = new WeekNo(Lock.X, 5);
        when(weekNoRepo.findById(Lock.X)).thenReturn(Optional.of(weekNo));

        // Act
        startingWeekService.StartWeek();

        // Assert
        verify(formationHistoryService, times(1)).SaveFormationHistory(5);
        assertEquals(6, weekNo.getWeekNo());
        verify(weekNoRepo, times(1)).save(weekNo);
    }

    @Test
    void testStartWeekWeekNoGreaterThan38() {
        // Arrange
        WeekNo weekNo = new WeekNo(Lock.X, 39);
        when(weekNoRepo.findById(Lock.X)).thenReturn(Optional.of(weekNo));

        // Act
        startingWeekService.StartWeek();

        // Assert
        verify(formationHistoryService, times(1)).SaveFormationHistory(39);
        assertEquals(0, weekNo.getWeekNo());
        verify(weekNoRepo, times(1)).save(weekNo);
    }
}

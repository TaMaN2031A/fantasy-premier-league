package com.fantasy.fantasyleague.fantasyGame.Service;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.fantasyGame.Model.PointHistory.PointHistory;
import com.fantasy.fantasyleague.fantasyGame.Repository.PointHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointHistoryService {

    final PointHistoryRepo pointHistoryRepo;
    @Autowired
    public PointHistoryService(PointHistoryRepo pointHistoryRepo) {
        this.pointHistoryRepo = pointHistoryRepo;
    }

    public void SavePointHistory(List<PointHistory> pointHistoryList) {
        try {
            pointHistoryRepo.saveAll(pointHistoryList);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public List<PointHistory>GetPlayersPointInNthWeek(List<Player> players, int weekNo){
        List<PointHistory>pointHistoryInNthWeek = new ArrayList<>();
        for(Player player:players){
            PointHistory pointHistory = pointHistoryRepo.findByPlayerAndWeekNo(player.getID(),weekNo);
            pointHistoryInNthWeek.add(pointHistory);
        }
        return pointHistoryInNthWeek;
    }
}

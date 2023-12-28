package com.fantasy.fantasyleague.fantasyGame.Service;

import com.fantasy.fantasyleague.fantasyGame.Model.Lock;
import com.fantasy.fantasyleague.fantasyGame.Model.WeekNo;
import com.fantasy.fantasyleague.fantasyGame.Repository.WeekNoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StartingWeekService{
    private final FormationHistoryService formationHistoryService;

    private final WeekNoRepo weekNoRepo;

    @Autowired
    public StartingWeekService(FormationHistoryService formationHistoryService, WeekNoRepo weekNoRepo) {
        this.formationHistoryService = formationHistoryService;
        this.weekNoRepo = weekNoRepo;
    }

    public void StartWeek(){
        Optional<WeekNo> weekNo = weekNoRepo.findById(Lock.X);
        int WeekNo = weekNo.get().getWeekNo();
        formationHistoryService.SaveFormationHistory(WeekNo);
        if(WeekNo>38){
            weekNo.get().setWeekNo(0);
            weekNoRepo.save(weekNo.get());
        }
        else {
            weekNo.get().setWeekNo(WeekNo + 1);
            weekNoRepo.save(weekNo.get());
        }
    }
}

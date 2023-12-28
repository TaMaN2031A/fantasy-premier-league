package com.fantasy.fantasyleague.fantasyGame.Controller;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.fantasyGame.Service.StartingWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/StartWeek")
public class StartWeekController {

    private final StartingWeekService startingWeekService;

    @Autowired
    public StartWeekController(StartingWeekService startingWeekService) {
        this.startingWeekService = startingWeekService;
    }

    @PutMapping("/Update")
    public void startNewWeek() {
         startingWeekService.StartWeek();
    }


}

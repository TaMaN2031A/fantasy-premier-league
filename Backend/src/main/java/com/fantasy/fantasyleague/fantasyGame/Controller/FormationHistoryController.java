package com.fantasy.fantasyleague.fantasyGame.Controller;

import com.fantasy.fantasyleague.fantasyGame.Service.FormationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/FormationHistory")
public class FormationHistoryController {
    private final FormationHistoryService formationHistoryService;

    @Autowired
    public FormationHistoryController(FormationHistoryService formationHistoryService) {
        this.formationHistoryService = formationHistoryService;
    }

    @GetMapping("/GetFormation")
    public void getFormationOfNthWeek(@PathVariable String userName, @PathVariable int WeekNo) {
        formationHistoryService.getFormationHistoryByUserAndWeek(userName,WeekNo);
    }
}

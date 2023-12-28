package com.fantasy.fantasyleague.fantasyGame.Controller;

import com.fantasy.fantasyleague.fantasyGame.DTO.FormationWithoutPointsDTO;
import com.fantasy.fantasyleague.fantasyGame.Service.PickTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/pickTeam")
public class PickTeamController {
    @Autowired
    private PickTeamService pickTeamService;

    @GetMapping("/getCurrentFormation/{username}")
    public ResponseEntity<FormationWithoutPointsDTO> getCurrentFormation(@PathVariable String username) {
        return pickTeamService.GetUserFantasyTeamFormation(username);
    }

}
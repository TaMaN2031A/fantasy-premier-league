package com.fantasy.fantasyleague.fantasyGame.Controller;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.fantasyGame.DTO.CompleteTeam;
import com.fantasy.fantasyleague.fantasyGame.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    private TransferService transferService;

    @GetMapping("/getGoalKeepers")
    public List<Player> getGoalKeepers() {
        return transferService.getGoalKeepers();
    }

    @GetMapping("/getDefenders")
    public List<Player> getDefenders() {
        return transferService.getDefenders();
    }

    @GetMapping("/getMidfielders")
    public List<Player> getMidfielders() {
        return transferService.getMidfielders();
    }

    @GetMapping("/getForwards")
    public List<Player> getForwards() {
        return transferService.getForwards();
    }

    @PutMapping("/update")
    public ResponseEntity<String> confirmTransfer(@RequestBody CompleteTeam completeTeam) {
        return transferService.confirmTransfer(completeTeam);
    }
}

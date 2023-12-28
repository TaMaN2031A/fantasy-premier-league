package com.fantasy.fantasyleague.fantasyGame.Controller;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.fantasyGame.DTO.CompleteTeam;
import com.fantasy.fantasyleague.fantasyGame.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    private TransferService transferService;

    @GetMapping("/getPlayer/{position}")
    public List<Player> getPlayer(@PathVariable String position) {
        List<Player> pl = transferService.getPlayers(position);
        System.out.println(pl.toString());
        return pl;
    }

    @PutMapping("/update")
    public ResponseEntity<String> confirmTransfer(@RequestBody CompleteTeam completeTeam) {
        return transferService.confirmTransfer(completeTeam);
    }
}
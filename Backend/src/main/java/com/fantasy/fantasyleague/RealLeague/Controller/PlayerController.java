package com.fantasy.fantasyleague.RealLeague.Controller;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    PlayerService playerService;
    @PostMapping("/insert")
    public String insertPlayer(@RequestBody Player player) {
        return playerService.insertPlayer(player);
    }

    @DeleteMapping("/delete")
    public String deletePlayer(@RequestBody Player player) {
        return playerService.deletePlayer(player);
    }
    @PostMapping("/update")
    public String updatePlayer(@RequestBody Player player) {
        return playerService.updatePlayer(player);
    }
    @GetMapping("/getAll")
    public List<Player> getAllPlayer() {
        return playerService.getAllPlayers();
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllPlayer() {
        return playerService.deleteAllPlayers();
    }
}
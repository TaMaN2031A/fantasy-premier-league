package com.fantasy.fantasyleague.RealLeague.Controller;

import com.fantasy.fantasyleague.RealLeague.DTO.PlayerDTO;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    PlayerService playerService;
    @PostMapping("/insert")
    public ResponseEntity insertPlayer(@RequestBody PlayerDTO playerDTO) {
        return playerService.insertPlayer(playerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePlayer(@PathVariable String id) {
        return playerService.deletePlayer(id);
    }
    @PostMapping("/update")
    public ResponseEntity updatePlayer(@RequestBody Player player) {
        return playerService.updatePlayer(player);
    }
    @GetMapping("/getAll")
    public List<Player> getAllPlayer() {
        return playerService.getAllPlayers();
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAllPlayer() {
        return playerService.deleteAllPlayers();
    }
}
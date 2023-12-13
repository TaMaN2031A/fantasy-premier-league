package com.fantasy.fantasyleague.RealLeague.Controller;


import com.fantasy.fantasyleague.RealLeague.DTO.MatchStatisticsDTO;
import com.fantasy.fantasyleague.RealLeague.Model.PlayedMatch;
import com.fantasy.fantasyleague.RealLeague.Service.MatchService;
import com.fantasy.fantasyleague.RealLeague.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/playedMatch")
public class PlayedMatchController {
    @Autowired
    MatchService matchService;

    @PostMapping("/insert")
    public ResponseEntity insertPlayedMatch(@RequestBody MatchStatisticsDTO match){
        return new ResponseEntity(matchService.addMatchStatiscis(match) , HttpStatus.OK) ;
    }

}

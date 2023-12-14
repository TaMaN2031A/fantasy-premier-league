package com.fantasy.fantasyleague.RealLeague.Controller;

import com.fantasy.fantasyleague.RealLeague.DTO.UpcomingMatchInsertionDTO;
import com.fantasy.fantasyleague.RealLeague.Model.UpcomingMatch;
import com.fantasy.fantasyleague.RealLeague.Service.UpcomingMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/upcomingMatch")
public class UpcomingMatchController {
    @Autowired
    UpcomingMatchService upcomingMatchService;
    @PostMapping("/insert")
    public ResponseEntity insertUpcomingMatch(@RequestBody UpcomingMatchInsertionDTO upcomingMatchInsertionDTO) {
        return upcomingMatchService.insertUpComingMatch(upcomingMatchInsertionDTO);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUpcomingMatch(@PathVariable String id) {
        return upcomingMatchService.deleteUpcomingMatch(id);
    }
    // If needed update we'll do it, right now no need
    @GetMapping("/getAll")
    public List<UpcomingMatch> getAllUpcomingMatch() {
        return upcomingMatchService.getAllUpcomingMatch();
    }
    @GetMapping("/getAll/{week}")
    public List<UpcomingMatch> getAllUpcomingMatch(@PathVariable int week) {
        return upcomingMatchService.getAllUpcomingMatch(week);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAllUpcomingMatch() {
        return upcomingMatchService.deleteAllUpcomingMatch();
    }
}
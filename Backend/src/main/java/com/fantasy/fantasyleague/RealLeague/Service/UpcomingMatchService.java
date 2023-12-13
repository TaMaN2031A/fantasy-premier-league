package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.DTO.UpcomingMatchInsertionDTO;
import com.fantasy.fantasyleague.RealLeague.Model.UpcomingMatch;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UpcomingMatchService {
    ResponseEntity insertUpComingMatch(UpcomingMatchInsertionDTO upcomingMatchInsertionDTO);
    ResponseEntity deleteUpcomingMatch(String id);

    // If needed update we'll do it, right now no need
    List<UpcomingMatch> getAllUpcomingMatch();
    List<UpcomingMatch> getAllUpcomingMatch(int week);
    ResponseEntity deleteAllUpcomingMatch();
    // For the statistics part, not the controller
    UpcomingMatch getMatch(int id);
}

package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.DTO.UpcomingMatchInsertionDTO;
import com.fantasy.fantasyleague.RealLeague.Model.UpcomingMatch;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.UpcomingMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UpcomingMatchServiceImpl implements UpcomingMatchService{
    @Autowired
    UpcomingMatchRepository upcomingMatchRepository;
    @Autowired
    TeamRepository teamRepository;
    public ResponseEntity insertUpComingMatch(UpcomingMatchInsertionDTO upcomingMatchInsertionDTO){
        Map<String, String> response = new HashMap<>();
        try {
            if(!teamRepository.existsById(upcomingMatchInsertionDTO.getAwayID()) ||
                    !teamRepository.existsById(upcomingMatchInsertionDTO.getHomeID() )) {
                response.put("error", "Teams doesn't exist");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if(upcomingMatchInsertionDTO.getWeek() < 0){
                response.put("error", "Wrong week number");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            UpcomingMatch upcomingMatch = new UpcomingMatch(
                    teamRepository.getReferenceById(upcomingMatchInsertionDTO.getHomeID()),
                    teamRepository.getReferenceById(upcomingMatchInsertionDTO.getAwayID()),
                    upcomingMatchInsertionDTO.getWeek(),
                    upcomingMatchInsertionDTO.getStadium()
            );
            upcomingMatchRepository.save(upcomingMatch);
            response.put("message", "Upcoming match inserted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("error", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity deleteUpcomingMatch(String ID){
        Map<String, String> response = new HashMap<>();
        try {
            int id = Integer.parseInt(ID);
            if(!upcomingMatchRepository.existsById(id)) {
                response.put("error", "Upcoming match ID doesn't exist");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            upcomingMatchRepository.deleteById(id);
            response.put("message", "Upcoming match deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("error", e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // If we needed update we'll do it, right now no need
    public List<UpcomingMatch> getAllUpcomingMatch(){
        return upcomingMatchRepository.findAll();
    }
    public List<UpcomingMatch> getAllUpcomingMatch(int week){
        return upcomingMatchRepository.findByWeek(week);
    }
    public ResponseEntity deleteAllUpcomingMatch(){
        Map<String, String> response = new HashMap<>();
        try{
            upcomingMatchRepository.deleteAll();
            response.put("message", "All Upcoming matches deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("error", e.toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
    public UpcomingMatch getMatch(int id){
        if(! upcomingMatchRepository.existsById(id))
            return null;
        return upcomingMatchRepository.getReferenceById(id);
    }

}

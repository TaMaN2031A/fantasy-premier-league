package com.fantasy.fantasyleague.fantasyGame.Service;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.fantasyGame.DTO.FormationWithoutPointsDTO;
import com.fantasy.fantasyleague.fantasyGame.DTO.PlayerWithoutPoints;
import com.fantasy.fantasyleague.fantasyGame.Model.Formation.CurrentFormation;
import com.fantasy.fantasyleague.fantasyGame.Repository.CurrentFormationRepo;
import com.fantasy.fantasyleague.fantasyGame.Repository.FormationHistoryRepo;
import com.fantasy.fantasyleague.fantasyGame.Repository.FormationStatusHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PickTeamService {
    private final UserRepository userRepository;
    private final CurrentFormationRepo currentFormationRepo;

    @Autowired
    public PickTeamService(FormationHistoryRepo formationHistoryRepo, UserRepository userRepository, CurrentFormationRepo currentFormationRepo, PointHistoryService pointHistoryService, FormationStatusHistoryRepo formationStatusHistoryRepo) {
        this.userRepository = userRepository;
        this.currentFormationRepo = currentFormationRepo;
    }

    public ResponseEntity<String> SaveFormation(String username,FormationWithoutPointsDTO formationWithoutPointsDTO){
        try {
            Optional<User> user = userRepository.findByUserName(username);
            if (user.isEmpty()) return ResponseEntity.notFound().build();
            user.get().setCaptain(formationWithoutPointsDTO.getCaptain());
            user.get().setViceCaptain(formationWithoutPointsDTO.getViceCaptain());
            user.get().setBenchBoost(formationWithoutPointsDTO.isBenchBoost());
            user.get().setTripleCaptain(formationWithoutPointsDTO.isTripleCaptain());
            userRepository.save(user.get());
            List<CurrentFormation> currentFormations = new ArrayList<>();
            for (PlayerWithoutPoints playerWithoutPoints : formationWithoutPointsDTO.getPlayers()) {
                currentFormations.add(new CurrentFormation(playerWithoutPoints.getPlayer(), user.get(), playerWithoutPoints.isStarter()));
            }
            currentFormationRepo.saveAll(currentFormations);
            return ResponseEntity.ok("Formation Saved");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }


    public ResponseEntity<FormationWithoutPointsDTO> GetUserFantasyTeamFormation(String username) {
        try {
            Optional<User> user = userRepository.findByUserName(username);
            if (user.isEmpty()) return ResponseEntity.notFound().build();
            List<CurrentFormation> PlayerIsStarterOrNotInNthWeek = currentFormationRepo.findByUser(username);
            List<Player> players = new ArrayList<>();
            List<String> teams = new ArrayList<>();
            for (CurrentFormation currentFormation : PlayerIsStarterOrNotInNthWeek) {
                players.add(currentFormation.getPlayer());
                teams.add(currentFormation.getPlayer().getTeam().getName());
            }
            List<PlayerWithoutPoints> PlayersInFantasyTeam = new ArrayList<>();
            for (int i = 0; i < players.size(); i++) {
                PlayersInFantasyTeam.add(new PlayerWithoutPoints(players.get(i), teams.get(i), PlayerIsStarterOrNotInNthWeek.get(i).isStarter()));
            }
            return ResponseEntity.ok(new FormationWithoutPointsDTO(PlayersInFantasyTeam, user.get().getCaptain(), user.get().getViceCaptain(), user.get().getBenchBoost(), user.get().getTripleCaptain()));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }


}

package com.fantasy.fantasyleague.fantasyGame.Service;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Position;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.fantasyGame.DTO.CompleteTeam;
import com.fantasy.fantasyleague.fantasyGame.Model.Lock;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.FormationHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.WeekNo;
import com.fantasy.fantasyleague.fantasyGame.Repository.FormationHistoryRepo;
import com.fantasy.fantasyleague.fantasyGame.Repository.WeekNoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService {
    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private WeekNoRepo weekNoRepo;

    @Autowired
    private FormationHistoryRepo formationHistoryRepo;


    public ResponseEntity<String> confirmTransfer(CompleteTeam completeTeam) {
        Optional<User> user = userRepo.findByUserName(completeTeam.getUsername());
        if (user.isPresent()) {
            List<Player> updatedPlayersList = collectPlayers(completeTeam.getPlayers());

            // check if the team is valid.
            boolean canContinue = manageTransferConditions(updatedPlayersList);
            if(!canContinue) return ResponseEntity.internalServerError().body("invalid team");

            // check number of changes in the team.
            if(weekNoRepo.count() == 0) return ResponseEntity.internalServerError().body("no week number stored");
            int prevWeekNo = weekNoRepo.findById(Lock.X).get().getWeekNo() - 1;
            List<FormationHistory> playersInPreviousWeek = formationHistoryRepo.findByUserAndWeekNum(completeTeam.getUsername(), prevWeekNo);

            if(!playersInPreviousWeek.isEmpty()) {
                int numNewTransfers = 15 - (int) playersInPreviousWeek.stream().map((fh) -> (fh.getPlayer().getID()))
                        .filter(id -> updatedPlayersList.stream().anyMatch(p -> p.getID() == id)).count();
                int numOfTransfers = user.get().getFreeTransfers();

                // check if the user has enough transfers.
                if(numNewTransfers > numOfTransfers) return ResponseEntity.internalServerError().body("not enough transfers");
                else {
                    user.get().setFreeTransfers(numOfTransfers - numNewTransfers);
                    userRepo.save(user.get());
                }
            }

            return ResponseEntity.ok("transfer successful");
        } else {
            return ResponseEntity.internalServerError().body("no such user");
        }
    }

    public boolean manageTransferConditions(List<Player> players) {
        if(players.stream().map(Player::getID).distinct().count() != 15) return false;
        if(players.stream().filter(p -> p.getPosition().equals(Position.GK.toString())).count() != 2) return false;
        if(players.stream().filter(p -> p.getPosition().equals(Position.DEF.toString())).count() != 5) return false;
        if(players.stream().filter(p -> p.getPosition().equals(Position.MID.toString())).count() != 5) return false;
        if(players.stream().filter(p -> p.getPosition().equals(Position.FWD.toString())).count() != 3) return false;
        return (players.stream().mapToDouble(Player::getPrice).sum() <= 100.0);
    }

    public List<Player> collectPlayers(List<String> team) {
        List<Player> players = new ArrayList<>();
        team.forEach(p -> {
            Player player = playerRepo.findByName(p);
            if(player != null) players.add(player);
        });
        return players;
    }

    public List<Player> getPlayers(String position) {
        return switch (position) {
            case "GK" -> getGoalKeepers();
            case "DF" -> getDefenders();
            case "MF" -> getMidfielders();
            case "FW" -> getForwards();
            default -> null;
        };
    }
    private List<Player> getGoalKeepers() {
        return playerRepo.findByPosition(Position.GK.toString());
    }

    private List<Player> getDefenders() {
        return playerRepo.findByPosition(Position.DEF.toString());
    }

    private List<Player> getMidfielders() {
        return playerRepo.findByPosition(Position.MID.toString());
    }

    private List<Player> getForwards() {
        return playerRepo.findByPosition(Position.FWD.toString());
    }
}
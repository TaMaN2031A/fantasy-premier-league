package com.fantasy.fantasyleague.fantasyGame.Service;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import com.fantasy.fantasyleague.fantasyGame.DTO.FormationDTO;
import com.fantasy.fantasyleague.fantasyGame.DTO.PlayerDTO;
import com.fantasy.fantasyleague.fantasyGame.DTO.PlayerWithoutPoints;
import com.fantasy.fantasyleague.fantasyGame.Model.Formation.CurrentFormation;
import com.fantasy.fantasyleague.fantasyGame.Model.FormationStatusHistory.FormationStatusHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.Lock;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.FormationHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.PointHistory.PointHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.WeekNo;
import com.fantasy.fantasyleague.fantasyGame.Repository.CurrentFormationRepo;
import com.fantasy.fantasyleague.fantasyGame.Repository.FormationHistoryRepo;
import com.fantasy.fantasyleague.fantasyGame.Repository.FormationStatusHistoryRepo;
import com.fantasy.fantasyleague.fantasyGame.Repository.WeekNoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormationHistoryService {
    private final FormationHistoryRepo formationHistoryRepo;
    private final UserRepository userRepository;
    private final CurrentFormationRepo currentFormationRepo;
    private final PointHistoryService pointHistoryService;
    private final FormationStatusHistoryRepo formationStatusHistoryRepo;

    @Autowired
    public FormationHistoryService(FormationHistoryRepo formationHistoryRepo, UserRepository userRepository, CurrentFormationRepo currentFormationRepo, PointHistoryService pointHistoryService, PlayerRepository playerRepository, FormationStatusHistoryRepo formationStatusHistoryRepo) {
        this.formationHistoryRepo = formationHistoryRepo;
        this.userRepository = userRepository;
        this.currentFormationRepo = currentFormationRepo;
        this.pointHistoryService = pointHistoryService;
        this.formationStatusHistoryRepo = formationStatusHistoryRepo;
    }

    public FormationDTO getFormationHistoryByUserAndWeek(String username, int weekNo) {
        List<FormationHistory> PlayerIsStarterOrNotInNthWeek = formationHistoryRepo.findByUserAndWeekNum(username, weekNo);
        List<Player> players = new ArrayList<>();
        List<String> teams = new ArrayList<>();
        for (FormationHistory formationHistory : PlayerIsStarterOrNotInNthWeek) {
            players.add(formationHistory.getPlayer());
            teams.add(formationHistory.getPlayer().getTeam().getName());
        }
        List<PointHistory> Points = pointHistoryService.GetPlayersPointInNthWeek(players, weekNo);
        List<PlayerDTO> PlayersInFantasyTeam = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            PlayersInFantasyTeam.add(new PlayerDTO(new PlayerWithoutPoints(players.get(i), teams.get(i), PlayerIsStarterOrNotInNthWeek.get(i).isStarter()), Points.get(i).getPoints()));
        }
        FormationStatusHistory formationStatusHistory = formationStatusHistoryRepo.findByUserAndWeekNum(username, weekNo);

        return new FormationDTO(PlayersInFantasyTeam, formationStatusHistory.getCaptain(), formationStatusHistory.getViceCaptain(), formationStatusHistory.isBenchBoost(), formationStatusHistory.isTripleCaptain());
    }

    public void SaveFormationHistory(int WeekNo) {
        List<User> users= userRepository.findAll();

        FormationHistory formationHistory;
        for (User user : users) {
            for (CurrentFormation currentFormation : currentFormationRepo.findByUser(user.getUserName())) {
                formationHistory = new FormationHistory(currentFormation.getPlayer(), user, WeekNo, currentFormation.isStarter());
                formationHistoryRepo.save(formationHistory);
            }
            user.setFreeTransfers(1);
            userRepository.save(user);
        }
    }
}

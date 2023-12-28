package com.fantasy.fantasyleague.fantasyGame.Service;


import com.fantasy.fantasyleague.RealLeague.DTO.MatchStatisticsDTO;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Repository.PlayerRepository;
import com.fantasy.fantasyleague.Registiration.Model.Response;
import com.fantasy.fantasyleague.fantasyGame.Model.Lock;
import com.fantasy.fantasyleague.fantasyGame.Model.PointHistory.PointHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.WeekNo;
import com.fantasy.fantasyleague.fantasyGame.Repository.PointHistoryRepo;
import com.fantasy.fantasyleague.fantasyGame.Repository.WeekNoRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PointsCalculator {

    @Autowired
    private PointHistoryRepo pointHistoryRepo;

    @Autowired
     private WeekNoRepo weekNoRepo;

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private PointHistoryService pointHistoryService;

    public Map<String, Long> convertToCountMap(List<String> playerExists) {
        return playerExists.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
    }

    public Map<String, Integer> handleHomePoints(MatchStatisticsDTO matchData) {
        Map<String, Integer> totalHomePoints = new HashMap<>();

        Map<String, Long> playerHomeGoalsMap = convertToCountMap(matchData.getHomePlayersScore());
        calculatePoints(totalHomePoints, playerHomeGoalsMap, goalPointsFunction);
        Map<String, Long> playerHomeAssistsMap = convertToCountMap(matchData.getHomePlayersAssist());
        calculatePoints(totalHomePoints, playerHomeAssistsMap, assistPointsFunction);

        Map<String, Long> playerHomeRedCardsMap = convertToCountMap(matchData.getHomePlayersRedCards());
        calculatePoints(totalHomePoints, playerHomeRedCardsMap, redCardPointsFunction);
        Map<String, Long> playerHomeYellowCardsMap = convertToCountMap(matchData.getHomePlayersYellowCards());
        calculatePoints(totalHomePoints, playerHomeYellowCardsMap, yellowCardPointsFunction);

        Map<String, Long> playerHomeCleanSheetsMap = convertToCountMap(matchData.getHomePlayersPlayed());
        calculatePoints(totalHomePoints, playerHomeCleanSheetsMap, cleanSheetPointsFunction);
        Map<String, Long> playerHomeSavesMap = convertToCountMap(matchData.getHomePlayersSaves());
        calculatePoints(totalHomePoints, playerHomeSavesMap, savePointsFunction);
        return totalHomePoints;
    }

    public Map<String, Integer> handleAwayPoints(MatchStatisticsDTO matchData) {
        Map<String, Integer> totalAwayPoints = new HashMap<>();

        Map<String, Long> playerAwayGoalsMap = convertToCountMap(matchData.getAwayPlayersScore());
        calculatePoints(totalAwayPoints, playerAwayGoalsMap, goalPointsFunction);
        Map<String, Long> playerAwayAssistsMap = convertToCountMap(matchData.getAwayPlayersAssist());
        calculatePoints(totalAwayPoints, playerAwayAssistsMap, assistPointsFunction);

        Map<String, Long> playerAwayRedCardsMap = convertToCountMap(matchData.getAwayPlayersRedCards());
        calculatePoints(totalAwayPoints, playerAwayRedCardsMap, redCardPointsFunction);
        Map<String, Long> playerAwayYellowCardsMap = convertToCountMap(matchData.getAwayPlayersYellowCards());
        calculatePoints(totalAwayPoints, playerAwayYellowCardsMap, yellowCardPointsFunction);

        Map<String, Long> playerAwayCleanSheetsMap = convertToCountMap(matchData.getAwayPlayersPlayed());
        calculatePoints(totalAwayPoints, playerAwayCleanSheetsMap, cleanSheetPointsFunction);
        Map<String, Long> playerAwaySavesMap = convertToCountMap(matchData.getAwayPlayersSaves());
        calculatePoints(totalAwayPoints, playerAwaySavesMap, savePointsFunction);

        return totalAwayPoints;
    }
    public void execute(MatchStatisticsDTO matchData) {
        Map<String, Integer> totalHomePoints = handleHomePoints(matchData);
        Map<String, Integer> totalAwayPoints = handleAwayPoints(matchData);

        if(weekNoRepo.count() == 0) { System.out.println("week number doesn't exists"); return; }
        int currentWeek = weekNoRepo.findById(Lock.X).get().getWeekNo();

        List<PointHistory> homeList = storePoints(totalHomePoints, currentWeek);
        List<PointHistory> awayList = storePoints(totalAwayPoints, currentWeek);

        pointHistoryService.SavePointHistory(homeList);
        pointHistoryService.SavePointHistory(awayList);

    }

    public List<PointHistory> storePoints(Map<String, Integer> totalPoints, int currentWeek) {
        List<PointHistory> pointHistoryList = new ArrayList<>();
        totalPoints.forEach(
            (playerName, points) -> {
                Player player = playerRepo.findByName(playerName);
                PointHistory pointHistory = new PointHistory(player, currentWeek, points);
                pointHistoryList.add(
                        pointHistoryRepo.save(
                                pointHistory
                        )
                );
            }
        );
        return pointHistoryList;
    }


    public void calculatePoints(Map<String, Integer> totalPointsOfPlayers,
                                Map<String, Long> playerGoalsMap,
                                BiFunction<String, Integer, Integer> functionCalculator) {
        // iterate on map and calculate points
        playerGoalsMap.forEach((playerName, count) -> {
            Player player = playerRepo.findByName(playerName);
            if(player == null) { System.out.println("player is null"); return; }
            Integer points = functionCalculator.apply(player.getPosition(), Math.toIntExact(count));
            if (points == null) { System.out.println("points value is null"); return; }
            totalPointsOfPlayers.merge(playerName, points, Integer::sum);
        });
    }

    BiFunction<String, Integer, Integer> goalPointsFunction = (position, goals) ->
            goals * switch (position) {
                case "GK", "DF" -> 6;
                case "MF" -> 5;
                case "FW" -> 4;
                default -> null;
            };

    BiFunction<String, Integer, Integer> assistPointsFunction = (position, assists) ->
            assists * switch (position) {
                case "GK", "DF", "MF", "FW" -> 3;
                default -> null;
            };

    BiFunction<String, Integer, Integer> cleanSheetPointsFunction = (position, cleanSheets) ->
            cleanSheets * switch (position) {
                case "GK", "DF" -> 4;
                case "MF", "FW" -> 1;
                default -> null;
            };

    BiFunction<String, Integer, Integer> redCardPointsFunction = (position, redCards) ->
            redCards * switch (position) {
                case "GK", "DF", "MF", "FW" -> -3;
                default -> null;
            };

    BiFunction<String, Integer, Integer> yellowCardPointsFunction = (position, yellowCards) ->
            yellowCards * switch (position) {
                case "GK", "DF", "MF", "FW" -> -1;
                default -> null;
            };

    BiFunction<String, Integer, Integer> savePointsFunction = (position, saves) ->
            switch (position) {
                case "GK" -> saves / 3;
                case "DF", "MF" -> saves / 2;
                case "FW" -> saves;
                default -> null;
            };

}

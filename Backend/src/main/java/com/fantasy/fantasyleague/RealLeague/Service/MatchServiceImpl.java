package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.DTO.MatchStatisticsDTO;
import com.fantasy.fantasyleague.RealLeague.Model.*;
import com.fantasy.fantasyleague.RealLeague.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchServiceImpl implements MatchService{

    @Autowired
    PlayerStatisticsRepoository playerStatisticsRepoository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    UpcomingMatchRepository upcomingMatchRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;


    @Override
    public String addMatchStatiscis(MatchStatisticsDTO playedMatchDTO){
        Team home = teamRepository.findByName(playedMatchDTO.getHome());
        Team away = teamRepository.findByName(playedMatchDTO.getAway());
        if(home == null || away == null){
            return "Teams not in the league.";
        }
        UpcomingMatch upcomingMatch = upcomingMatchRepository.findByHomeAndAway(home , away) ;
        if(upcomingMatch != null){
            PlayedMatch match = UpcomingMatchToPlayedMatch(upcomingMatch , playedMatchDTO);
            matchRepository.save(match);
            generatePlayerStatistics(playedMatchDTO, match);
        }else{
            return "Match is not in this week.";
        }
        return "Match is added successfully";
    }



    private void generatePlayerStatistics(MatchStatisticsDTO playedMatchDTO, PlayedMatch match) {
        List<String> awayPlayers = playedMatchDTO.getAwayPlayersPlayed();
        List<String> homePlayers = playedMatchDTO.getHomePlayersPlayed();
        for(String name : awayPlayers){
            buildPlayerStatistics(playedMatchDTO, match , false , name );
        }
        for(String name : homePlayers){
            buildPlayerStatistics(playedMatchDTO, match , true , name );
        }

    }

    public int countStrings(List<String> stringList, String targetString) {
        int count = 0;

        // Iterate through the list
        for (String str : stringList) {
            // Compare each element with the target string
            if (str.equals(targetString)) {
                count++;
            }
        }
        return count;
    }

    public void buildPlayerStatistics(MatchStatisticsDTO playedMatchDTO, PlayedMatch match , boolean isHome, String name){
        int goals =0 , yellowCards  = 0 , redCards= 0  , assists = 0 , saves = 0;
        if(isHome){
            goals = countStrings(playedMatchDTO.getHomePlayersScore() , name);
            yellowCards = countStrings(playedMatchDTO.getHomePlayersYellowCards() , name);
            redCards = countStrings(playedMatchDTO.getHomePlayersRedCards() , name);
            assists = countStrings(playedMatchDTO.getHomePlayersAssist() , name);
            saves = countStrings(playedMatchDTO.getHomePlayersSaves() , name);
        }else{
            goals = countStrings(playedMatchDTO.getAwayPlayersScore() , name);
            yellowCards = countStrings(playedMatchDTO.getAwayPlayersYellowCards() , name);
            redCards = countStrings(playedMatchDTO.getAwayPlayersRedCards() , name);
            assists = countStrings(playedMatchDTO.getAwayPlayersAssist() , name);
            saves = countStrings(playedMatchDTO.getAwayPlayersSaves() , name);
        }
        boolean playerOfMatch = name.equals(playedMatchDTO.getManOfMatch());
        Player player = playerRepository.findByName(name);
        PlayerStatistics statistics1;
        if(player != null){
            statistics1 = PlayerStatistics.builder()
                    .match(match)
                    .player(player)
                    .yellowCards(yellowCards)
                    .saves(saves)
                    .assists(assists)
                    .redCards(redCards)
                    .man_of_match(playerOfMatch)
                    .goal(goals)
                    .build();
            playerStatisticsRepoository.save(statistics1);
            updatePlayerData(player , statistics1);
        }

    }

    void updatePlayerData(Player player , PlayerStatistics statistics){
        player.setAssists(player.getAssists() + statistics.getAssists());
        player.setGoals(player.getGoals() + statistics.getGoal());
        player.setRed_cards(player.getRed_cards() + statistics.getRedCards());
        player.setYellow_cards(player.getYellow_cards() + statistics.getYellowCards()) ;
        player.setSaved(player.getSaved() + statistics.getSaves());
        playerRepository.save(player);
    }


    public PlayedMatch UpcomingMatchToPlayedMatch(UpcomingMatch upcomingMatch , MatchStatisticsDTO matchStatisticsDTO){
        PlayedMatch  match= PlayedMatch.builder()
                .away(upcomingMatch.getAway())
                .home(upcomingMatch.getHome())
                .date(new Date())
                .redCardsAway(matchStatisticsDTO.getAwayPlayersRedCards().size())
                .redCardsHome(matchStatisticsDTO.getHomePlayersRedCards().size())
                .yellowCardsAway(matchStatisticsDTO.getAwayPlayersYellowCards().size())
                .yellowCardsHome(matchStatisticsDTO.getHomePlayersYellowCards().size())
                .week(upcomingMatch.getWeek())
                .homeGoals(matchStatisticsDTO.getHomePlayersScore().size())
                .awayGoals(matchStatisticsDTO.getAwayPlayersScore().size())
                .stadium(upcomingMatch.getStadium())
                .build();
        upcomingMatchRepository.deleteById(upcomingMatch.getID());
        updateTeamData(upcomingMatch.getHome() , match , true);
        updateTeamData(upcomingMatch.getAway() , match , false);
        return match;
    }

    public void updateTeamData(Team team , PlayedMatch match , boolean isHome){
        if(isHome){
            team.setGoals_for(team.getGoals_for() + match.getHomeGoals());
            team.setGoals_difference(team.getGoals_difference() + match.getHomeGoals() - match.getAwayGoals());
            team.setRed_cards(team.getRed_cards() + match.getRedCardsHome());
            team.setYellow_cards(team.getYellow_cards() + match.getYellowCardsHome());
            if(match.getHomeGoals() > match.getAwayGoals()){
                team.setPoints(team.getPoints() + 3);
            }else if(match.getHomeGoals() == match.getAwayGoals()){
                team.setPoints(team.getPoints() + 1);
            }

        }else{
            team.setGoals_for(team.getGoals_for() + match.getAwayGoals());
            team.setGoals_difference(team.getGoals_difference() +match.getAwayGoals() - match.getHomeGoals() );
            team.setRed_cards(team.getRed_cards() + match.getRedCardsAway());
            team.setYellow_cards(team.getYellow_cards() + match.getYellowCardsAway());
            if(match.getHomeGoals() < match.getAwayGoals()){
                team.setPoints(team.getPoints() + 3);
            }else if(match.getHomeGoals() == match.getAwayGoals()){
                team.setPoints(team.getPoints() + 1);
            }
        }
        teamRepository.save(team);
    }
}
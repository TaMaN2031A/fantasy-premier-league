package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.DTO.MatchStatisticsDTO;
import com.fantasy.fantasyleague.RealLeague.Model.*;
import com.fantasy.fantasyleague.RealLeague.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService{

    final
    PlayerStatisticsRepoository playerStatisticsRepoository;

    final
    MatchRepository matchRepository;

    final
    UpcomingMatchRepository upcomingMatchRepository;

    final
    PlayerRepository playerRepository;

    final
    TeamRepository teamRepository;

    @Autowired
    public MatchServiceImpl(PlayerStatisticsRepoository playerStatisticsRepoository, MatchRepository matchRepository, UpcomingMatchRepository upcomingMatchRepository, PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerStatisticsRepoository = playerStatisticsRepoository;
        this.matchRepository = matchRepository;
        this.upcomingMatchRepository = upcomingMatchRepository;
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }


    @Override
    public String addMatchStatiscis(MatchStatisticsDTO playedMatchDTO){
        Team home = teamRepository.findByName(playedMatchDTO.getHome());
        Team away = teamRepository.findByName(playedMatchDTO.getAway());
        if(home == null || away == null){
            return "Teams not in the league.";
        }
        UpcomingMatch upcomingMatch = upcomingMatchRepository.findByHomeAndAway(home , away) ;
        if(upcomingMatch == null) return "Match is not in this week.";

        PlayedMatch match = UpcomingMatchToPlayedMatch(upcomingMatch , playedMatchDTO);
        matchRepository.save(match);
        generatePlayerStatistics(playedMatchDTO, match);

        return "Match is added successfully";
    }



    private void generatePlayerStatistics(MatchStatisticsDTO playedMatchDTO, PlayedMatch match) {
        List<String> awayPlayers = playedMatchDTO.getAwayPlayersPlayed();
        List<String> homePlayers = playedMatchDTO.getHomePlayersPlayed();
        awayPlayers.forEach(name -> buildPlayerStatistics(playedMatchDTO, match , false , name ));
        homePlayers.forEach(name -> buildPlayerStatistics(playedMatchDTO, match , true , name ));

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
        int goals  , yellowCards   , redCards  , assists , saves , cleanSheet ;
        if(isHome){
            goals = countStrings(playedMatchDTO.getHomePlayersScore() , name);
            yellowCards = countStrings(playedMatchDTO.getHomePlayersYellowCards() , name);
            redCards = countStrings(playedMatchDTO.getHomePlayersRedCards() , name);
            assists = countStrings(playedMatchDTO.getHomePlayersAssist() , name);
            saves = countStrings(playedMatchDTO.getHomePlayersSaves() , name);
            cleanSheet = match.getAwayGoals() == 0 ? 1 : 0;
        } else {
            goals = countStrings(playedMatchDTO.getAwayPlayersScore() , name);
            yellowCards = countStrings(playedMatchDTO.getAwayPlayersYellowCards() , name);
            redCards = countStrings(playedMatchDTO.getAwayPlayersRedCards() , name);
            assists = countStrings(playedMatchDTO.getAwayPlayersAssist() , name);
            saves = countStrings(playedMatchDTO.getAwayPlayersSaves() , name);
            cleanSheet = match.getHomeGoals() == 0 ? 1 : 0;
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
            updatePlayerData(player , statistics1, cleanSheet);
        }

    }

    void updatePlayerData(Player player , PlayerStatistics statistics, int cleanSheet){
        player.setAssists(player.getAssists() + statistics.getAssists());
        player.setGoals(player.getGoals() + statistics.getGoal());
        player.setRed_cards(player.getRed_cards() + statistics.getRedCards());
        player.setYellow_cards(player.getYellow_cards() + statistics.getYellowCards()) ;
        player.setSaved(player.getSaved() + statistics.getSaves());
        player.setClean_sheet(player.getClean_sheet() + cleanSheet);
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
package com.fantasy.fantasyleague.RealLeague.Service;

import com.fantasy.fantasyleague.RealLeague.Model.PlayedMatch;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Repository.MatchRepository;
import com.fantasy.fantasyleague.RealLeague.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueStatisticsService {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MatchRepository matchRepository;

    public List<Team> getLeagueStandings() {
        List<Team> nonSortedTeams = teamRepository.findAll();
        return sortTeams(nonSortedTeams);
    }

    /*
    * If any clubs finish with the same number of points,
    * their position in the Premier League table is determined by goal difference,
    * then the number of goals scored,
    * then the team who collected the most points in the head-to-head matches,
    * then who scored most away goals in the head-to-head.
    */
    private List<Team> sortTeams(List<Team> nonSortedTeams) {
        nonSortedTeams.sort((team1, team2) -> {
            int pointsDifference = team2.getPoints() - team1.getPoints();
            if (pointsDifference != 0) {
                return pointsDifference;
            }

            int goalDifference = (team2.getGoals_for() - team2.getGoals_conceded()) - (team1.getGoals_for() - team1.getGoals_conceded());
            if (goalDifference != 0) {
                return goalDifference;
            }

            int goalsFor = team2.getGoals_for() - team1.getGoals_for();
            if (goalsFor != 0) {
                return goalsFor;
            }
            // get needed data from database
            PlayedMatch firstMatch = matchRepository.findByHomeAndAway(team1, team2);
            PlayedMatch secondMatch = matchRepository.findByHomeAndAway(team2, team1);
            int pointsT1 = 0, pointsT2 = 0;
            int[] points = getPointsOfMatch(firstMatch);
            pointsT1 += points[0];
            pointsT2 += points[1];
            points = getPointsOfMatch(secondMatch);
            pointsT1 += points[1];
            pointsT2 += points[0];

            int pointsDifferenceHeadToHead = pointsT2 - pointsT1;
            if (pointsDifferenceHeadToHead != 0) {
                return pointsDifferenceHeadToHead;
            }

            int mostAwayGoalsHeadToHead =
                    getMostAwayGoals(firstMatch) - getMostAwayGoals(secondMatch);
            if (mostAwayGoalsHeadToHead != 0) {
                return mostAwayGoalsHeadToHead;
            }
            return team1.getName().compareTo(team2.getName());
        });
        return nonSortedTeams;
    }

    private int[] getPointsOfMatch(PlayedMatch match) {
        int[] points = new int[]{0, 0};
        if(match == null) return points;

        if (match.getHomeGoals() > match.getAwayGoals()) {
            points[0] = 3;
        } else if (match.getHomeGoals() < match.getAwayGoals()) {
            points[1] = 3;
        } else {
            points[0] = 1;
            points[1] = 1;
        }
        return points;
    }
    private int getMostAwayGoals(PlayedMatch match) {
        if(match == null) return 0;
        return match.getAwayGoals();
    }
}

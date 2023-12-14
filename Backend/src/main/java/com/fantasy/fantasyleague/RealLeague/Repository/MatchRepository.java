package com.fantasy.fantasyleague.RealLeague.Repository;

import com.fantasy.fantasyleague.RealLeague.Model.PlayedMatch;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends
        JpaRepository<PlayedMatch, Integer> {
    List<PlayedMatch> findByWeek(int week);

    @Query(value = "SELECT * FROM Played_match u WHERE u.home = ?1 AND u.away = ?2" , nativeQuery = true)
    List<PlayedMatch> findAllMatches(int home , int away);
    PlayedMatch findByHomeAndAway(Team home, Team away);

    boolean deleteById(int id);

    PlayedMatch save(PlayedMatch playedMatch);

}
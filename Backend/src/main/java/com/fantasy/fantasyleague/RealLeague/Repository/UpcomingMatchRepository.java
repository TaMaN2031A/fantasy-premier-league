package com.fantasy.fantasyleague.RealLeague.Repository;

import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Model.UpcomingMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UpcomingMatchRepository extends
        JpaRepository<UpcomingMatch, Integer> {
    List<UpcomingMatch> findByWeek(int weekNumber);
    UpcomingMatch findByHomeAndAway(Team home , Team away);

}
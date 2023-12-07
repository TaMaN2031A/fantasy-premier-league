package com.fantasy.fantasyleague.RealLeague.Repository;

import com.fantasy.fantasyleague.RealLeague.Model.UpcomingMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UpcomingMatchRepository extends
        JpaRepository<UpcomingMatch, Integer> {
    List<UpcomingMatch> findByWeek(int weekNumber);
}
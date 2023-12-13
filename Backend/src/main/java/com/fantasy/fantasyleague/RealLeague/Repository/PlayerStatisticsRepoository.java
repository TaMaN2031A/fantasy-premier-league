package com.fantasy.fantasyleague.RealLeague.Repository;

import com.fantasy.fantasyleague.RealLeague.Model.PlayedMatch;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.PlayerStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerStatisticsRepoository extends
        JpaRepository<PlayerStatistics, Integer>  {

    PlayerStatistics save(PlayerStatistics playerStatistics);
    List<PlayerStatistics> findAllByMatch(PlayedMatch match);
}



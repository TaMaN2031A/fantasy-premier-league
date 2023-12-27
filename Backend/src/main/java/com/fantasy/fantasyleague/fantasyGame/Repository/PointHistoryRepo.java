package com.fantasy.fantasyleague.fantasyGame.Repository;

import com.fantasy.fantasyleague.fantasyGame.Model.PointHistory.PointHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.PointHistory.PointHistoryComposite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointHistoryRepo extends
        JpaRepository<PointHistory, PointHistoryComposite> {
    @Query(value = "SELECT * FROM point_history WHERE player_id = ?1 AND week_no = ?2", nativeQuery = true)
    PointHistory findByPlayerPointAndWeekNo(int playerId, int weekNo);

    @Query(value = "SELECT * FROM point_history WHERE week_no = ?1", nativeQuery = true)
    List<PointHistory> findAllByWeekNo(int weekNo);
}

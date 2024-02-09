package com.fantasy.fantasyleague.fantasyGame.Repository;

import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.FormationHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.FormationHistoryComposite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationHistoryRepo extends
        JpaRepository<FormationHistory, FormationHistoryComposite> {
    @Query("select f from FormationHistory f where f.player.ID = ?1 and f.user.userName = ?2 and f.weekNum = ?3")
        FormationHistory findByPlayerAndUserAndWeekNum(int playerId, String userName, int weekNum);

    @Query("select f from FormationHistory f where f.user.userName = ?1 and f.weekNum = ?2")
    List<FormationHistory> findByUserAndWeekNum(String userName, int weekNum);
    @Override
    <S extends FormationHistory> List<S> saveAll(Iterable<S> entities);
}

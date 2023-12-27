package com.fantasy.fantasyleague.fantasyGame.Repository;

import com.fantasy.fantasyleague.fantasyGame.Model.FormationStatusHistory.FormationStatusHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.FormationStatusHistory.FormationStatusHistoryComposite;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.FormationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationStatusHistoryRepo extends
        JpaRepository<FormationStatusHistory, FormationStatusHistoryComposite> {
    @Override
    <S extends FormationStatusHistory> S save(S formationStatusHistory);
    @Query("select f from FormationStatusHistory f where f.user.userName = ?1 and f.week_no = ?2")
    FormationStatusHistory findByUserAndWeekNum(String userName, int weekNum);
}

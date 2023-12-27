package com.fantasy.fantasyleague.fantasyGame.Repository;

import com.fantasy.fantasyleague.fantasyGame.Model.FormationStatusHistory.FormationStatusHistory;
import com.fantasy.fantasyleague.fantasyGame.Model.FormationStatusHistory.FormationStatusHistoryComposite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationStatusHistoryRepo extends
        JpaRepository<FormationStatusHistory, FormationStatusHistoryComposite> {
}

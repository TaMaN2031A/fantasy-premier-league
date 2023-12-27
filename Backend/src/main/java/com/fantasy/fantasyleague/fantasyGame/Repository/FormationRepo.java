package com.fantasy.fantasyleague.fantasyGame.Repository;

import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.Formation;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.FormationComposite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepo extends
        JpaRepository<Formation, FormationComposite> {
}

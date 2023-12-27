package com.fantasy.fantasyleague.fantasyGame.Repository;

import com.fantasy.fantasyleague.fantasyGame.Model.Formation.CurrentFormation;
import com.fantasy.fantasyleague.fantasyGame.Model.Formation.CurrentFormationComposite;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.FormationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrentFormationRepo extends
        JpaRepository<CurrentFormation, CurrentFormationComposite> {
    @Query("select f from CurrentFormation f where f.user.userName = ?1")
    List<CurrentFormation> findByUser(String userName);

    @Override
    <S extends CurrentFormation> List<S> saveAll(Iterable<S> entities);
}

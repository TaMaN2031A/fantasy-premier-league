package com.fantasy.fantasyleague.RealLeague.Repository;

import com.fantasy.fantasyleague.RealLeague.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TeamRepository extends
        JpaRepository<Team, Integer> {
    Team findByName(String name);
}
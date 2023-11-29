package com.fantasy.fantasyleague.RealLeague.Repository;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends
        JpaRepository<Player, Integer> {
    Optional<Player> findByName(String name);

}
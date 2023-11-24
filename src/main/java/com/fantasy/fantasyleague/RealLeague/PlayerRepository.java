package com.fantasy.fantasyleague.RealLeague;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends
        JpaRepository<Player, Integer> {

}

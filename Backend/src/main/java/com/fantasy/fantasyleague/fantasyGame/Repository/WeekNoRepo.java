package com.fantasy.fantasyleague.fantasyGame.Repository;

import com.fantasy.fantasyleague.fantasyGame.Model.Lock;
import com.fantasy.fantasyleague.fantasyGame.Model.WeekNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeekNoRepo extends JpaRepository<WeekNo, Lock> {
//    WeekNo findByLock(WeekNo.Lock lock);

    @Override
    Optional<WeekNo> findById(Lock lock);

    @Override
    <S extends WeekNo> S save(S entity);



}

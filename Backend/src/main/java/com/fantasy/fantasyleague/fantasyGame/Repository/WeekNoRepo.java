package com.fantasy.fantasyleague.fantasyGame.Repository;

import com.fantasy.fantasyleague.fantasyGame.Model.WeekNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekNoRepo extends JpaRepository<WeekNo, WeekNo.Lock> {
    WeekNo findByLock(WeekNo.Lock lock);

    @Query(value = "UPDATE WeekNo SET weekNo = weekNo + 1 WHERE lock = 'X'")
    void incrementWeek();
}

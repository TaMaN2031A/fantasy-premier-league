package com.fantasy.fantasyleague.FantasyGame.Repo;
import com.fantasy.fantasyleague.fantasyGame.Model.Lock;
import com.fantasy.fantasyleague.fantasyGame.Model.WeekNo;
import com.fantasy.fantasyleague.fantasyGame.Repository.WeekNoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class WeekNoRepoTest {

    @Autowired
    private WeekNoRepo weekNoRepo;

    @Test
    void InsertTest(){
        WeekNo weekNo = new WeekNo(Lock.X,5);
        weekNoRepo.save(weekNo);
        weekNoRepo.incrementWeek();
        assertEquals(weekNoRepo.count(),1);
    }

//    @Test
//    void GetTest(){
////        int weekNo = this.weekNoRepo.findByLock(WeekNo.Lock.X).getWeekNo();
//        assertEquals(weekNo, 0);
//    }

//    @Test
//    void UpdateTest(){
//        WeekNo initialWeekNo = new WeekNo(WeekNo.Lock.X, 1);
//        weekNoRepo.save(initialWeekNo);
//
//        WeekNo updatedWeekNo = new WeekNo(WeekNo.Lock.X, 2);
//        weekNoRepo.save(updatedWeekNo);
//
////        int weekNo = this.weekNoRepo.findByLock(WeekNo.Lock.X).getWeekNo();
//
//        assertEquals(weekNo, 2);
//    }
}

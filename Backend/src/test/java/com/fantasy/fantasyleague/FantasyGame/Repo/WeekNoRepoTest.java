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
        WeekNo weekNo = new WeekNo(Lock.X,1);
        weekNoRepo.save(weekNo);
        assertEquals(weekNoRepo.count(),1);
    }

    @Test
    void GetTest(){
        weekNoRepo.save(new WeekNo(Lock.X, 0));
        int weekNo = this.weekNoRepo.findById(Lock.X).get().getWeekNo();
        assertEquals(weekNo, 0);
    }

    @Test
    void ResetTest(){
        weekNoRepo.save(new WeekNo(Lock.X, 39));
        int weekNo = weekNoRepo.findById(Lock.X).get().getWeekNo();
        assertEquals(weekNo, 39);
        if(weekNo>38){
            weekNoRepo.save(new WeekNo(Lock.X, 0));
        }
        assertEquals(weekNoRepo.findById(Lock.X).get().getWeekNo(), 0);
    }


}

package com.fantasy.fantasyleague.RealLeague.Repository;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Model.UpcomingMatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UpcomingMatchRepositoryTests {
    @Autowired
    private UpcomingMatchRepository upcomingMatchRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Test
    public void TeamRepository_SaveThenExistByID_FindWhatWeSaved() {
        // Arrange
        Team team1 = new Team("Al Ahly");
        Team team2 =  new Team("El Zamalek");
        teamRepository.save(team2); teamRepository.save(team1);
        UpcomingMatch upcomingMatch = new UpcomingMatch(team1, team2, 1, "El Salam");
        //Act
        this.upcomingMatchRepository.save(upcomingMatch);
        //Assert
        Assertions.assertTrue(this.upcomingMatchRepository.existsById(upcomingMatch.getID()));
    }

    @Test
    public void PlayerRepository_BulkInsertThenFindAll_FindWhatWeSaved() {
        // Arrange
        Team team1 = new Team("Al Ahly");
        Team team2 =  new Team("El Zamalek");
        teamRepository.save(team2); teamRepository.save(team1);
        UpcomingMatch upcomingMatch = new UpcomingMatch(team1, team2, 1, "El Salam");
        UpcomingMatch upcomingMatch2 = new UpcomingMatch(team2, team1, 1, "El Salam");
        //Act
        this.upcomingMatchRepository.save(upcomingMatch);
        this.upcomingMatchRepository.save(upcomingMatch2);
        // Assert
        Assertions.assertEquals(2, this.upcomingMatchRepository.findAll().size());
        Assertions.assertEquals("Al Ahly", this.upcomingMatchRepository.findAll().get(0).getHome().getName());
        Assertions.assertEquals("El Zamalek", this.upcomingMatchRepository.findAll().get(1).getHome().getName());
    }

    @Test
    public void PlayerRepository_FindNonExisting_FindWhatWeSaved() {
        // Arrange
        Team team1 = new Team("Al Ahly");
        Team team2 =  new Team("El Zamalek");
        teamRepository.save(team2); teamRepository.save(team1);
        UpcomingMatch upcomingMatch = new UpcomingMatch(team1, team2, 1, "El Salam");
        //Act
        this.upcomingMatchRepository.save(upcomingMatch);
        // Assert
        Assertions.assertFalse(this.upcomingMatchRepository.existsById(upcomingMatch.getID()+1));
    }

    @Test
    public void PlayerRepository_DeleteAnExistingAndMakeSureItsDeleted_FindWhatWeSaved() {
        // Arrange
        Team team1 = new Team("Al Ahly");
        Team team2 =  new Team("El Zamalek");
        teamRepository.save(team2); teamRepository.save(team1);
        UpcomingMatch upcomingMatch = new UpcomingMatch(team1, team2, 1, "El Salam");
        //Act
        this.upcomingMatchRepository.save(upcomingMatch);
        this.upcomingMatchRepository.deleteById(upcomingMatch.getID());
        // Assert
        Assertions.assertFalse(upcomingMatchRepository.existsById(upcomingMatch.getID()));
    }

    @Test
    public void PlayerRepository_BatchSaveThenBatchDeleteCheckSize_FindWhatWeSaved() {
        // Arrange
        Team team1 = new Team("Al Ahly");
        Team team2 =  new Team("El Zamalek");
        teamRepository.save(team2); teamRepository.save(team1);

        for (int i = 1; i < 100; i++) {
            UpcomingMatch upcomingMatch = new UpcomingMatch(team1, team2, i, "El Salam");
            // Act
            this.upcomingMatchRepository.save(upcomingMatch);
        }
        //Act
        this.upcomingMatchRepository.deleteAll();
        // Assert
        Assertions.assertEquals(0, upcomingMatchRepository.findAll().size());
    }

}
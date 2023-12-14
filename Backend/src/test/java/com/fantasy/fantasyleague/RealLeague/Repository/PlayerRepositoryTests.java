package com.fantasy.fantasyleague.RealLeague.Repository;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PlayerRepositoryTests {
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void TeamRepository_SaveThenExistByID_FindWhatWeSaved() {
        // Arrange
        Player player = new Player("Ibrahim El Nabulsi", "ST", 19, 1);
        //Act
        this.playerRepository.save(player);
        //Assert
        Assertions.assertTrue(this.playerRepository.existsById(player.getID()));
    }

    @Test
    public void PlayerRepository_BulkInsertThenFindAll_FindWhatWeSaved() {
        // Arrange
        Player player = new Player("Ibrahim El Nabulsi", "ST", 19, 1);
        Player player2 = new Player("Sameeh Abo El Wafa", "ST", 19, 1);
        //Act
        this.playerRepository.save(player);
        this.playerRepository.save(player2);
        // Assert
        Assertions.assertEquals(2, this.playerRepository.findAll().size());
        Assertions.assertEquals("Ibrahim El Nabulsi", this.playerRepository.findAll().get(0).getName());
        Assertions.assertEquals("Sameeh Abo El Wafa", this.playerRepository.findAll().get(1).getName());
    }

    @Test
    public void PlayerRepository_FindByIDThenUpdateAndSaveThenFindAllAndCheckNames_FindWhatWeSaved() {
        // Arrange
        Player player = new Player("Ibrahim El Nabulsi", "ST", 19, 1);
        //Act
        this.playerRepository.save(player);
        Player player1 = this.playerRepository.findById(player.getID()).orElse(null);
        assert player1 != null;
        player1.setName("Sameeh Abo El Wafa");
        this.playerRepository.save(player1);
        // Assert
        Assertions.assertEquals(1, this.playerRepository.findAll().size());
        Assertions.assertEquals("Sameeh Abo El Wafa", this.playerRepository.findAll().get(0).getName());
        Assertions.assertNotEquals("Ibrahim El Nabulsi", this.playerRepository.findAll().get(0).getName());
    }

    @Test
    public void PlayerRepository_FindNonExisting_FindWhatWeSaved() {
        // Arrange
        Player player = new Player("Ibrahim El Nabulsi", "ST", 19, 1);
        //Act
        this.playerRepository.save(player);
        // Assert
        Assertions.assertFalse(this.playerRepository.existsById(player.getID() + 1));
    }

    @Test
    public void PlayerRepository_DeleteAnExistingAndMakeSureItsDeleted_FindWhatWeSaved() {
        // Arrange
        Player player = new Player("Ibrahim El Nabulsi", "ST", 19, 1);
        //Act
        this.playerRepository.save(player);
        this.playerRepository.deleteById(player.getID());
        // Assert
        Assertions.assertFalse(playerRepository.existsById(player.getID()));
    }

    @Test
    public void PlayerRepository_BatchSaveThenBatchDeleteCheckSize_FindWhatWeSaved() {
        // Arrange
        String s = "";
        for (int i = 0; i < 100; i++) {
            s += "a";
            Player player = new Player(s, "ST", 19, 1);
            // Act
            this.playerRepository.save(player);
        }
        //Act
        this.playerRepository.deleteAll();
        // Assert
        Assertions.assertEquals(0, playerRepository.findAll().size());
    }

}
package com.fantasy.fantasyleague.RealLeague.Repository;

import com.fantasy.fantasyleague.RealLeague.Model.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TeamRepositoryTests {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    EntityManager entityManager;
    @Test
    public void TeamRepository_SaveThenExistByID_FindWhatWeSaved(){
        // Arrange
        Team team = new Team("Kom Hamada");
        //Act
        this.teamRepository.save(team);
        //Assert
        Assertions.assertTrue(this.teamRepository.existsById(team.getID()));
    }

    @Test
    public void TeamRepository_Save_FindBYName(){
        // Arrange
        Team team = new Team("Kom Hamada");
        //Act
        this.teamRepository.save(team);
        //Assert
        Assertions.assertNotNull(this.teamRepository.findByName(team.getName()));
    }

    @Test
    public void TeamRepository_BulkInsertThenFindAllAndCheckIDsPositive_FindWhatWeSaved(){
        // Arrange
        Team team = new Team("Kom Hamada");
        //Act

        this.teamRepository.save(team);
        Team team1 = this.teamRepository.findByName(team.getName());
        assert team1 != null;
        team1.setName("Itay EL Baroud");
        this.teamRepository.save(team1);


        // Assert
        Assertions.assertEquals(1, this.teamRepository.findAll().size());
        Assertions.assertEquals("Itay EL Baroud", this.teamRepository.findAll().get(0).getName());
        Assertions.assertNotEquals("Kom Hamada", this.teamRepository.findAll().get(0).getName());

    }

    @Test
    public void TeamRepository_FindByNameThenUpdateAndSaveThenFindAllAndCheckNames_FindWhatWeSaved(){
        // Arrange
        Team team = new Team("Kom Hamada");
        //Act

        this.teamRepository.save(team);
        Team team1 = this.teamRepository.findByName(team.getName());
        assert team1 != null;
        team1.setName("Itay EL Baroud");
        this.teamRepository.save(team1);


        // Assert
        Assertions.assertEquals(1, this.teamRepository.findAll().size());
        Assertions.assertEquals("Itay EL Baroud", this.teamRepository.findAll().get(0).getName());
        Assertions.assertNotEquals("Kom Hamada", this.teamRepository.findAll().get(0).getName());

    }

    @Test
    public void TeamRepository_FindNonExisting_FindWhatWeSaved(){
        // Arrange
        Team team = new Team("Kom Hamada");
        //Act

        this.teamRepository.save(team);
        // Assert
        Team team1 = this.teamRepository.findByName("Itay El Baroud");
        Assertions.assertNull(team1);

    }

    @Test
    public void TeamRepository_DeleteAnExistingAndMakeSureItsDeleted_FindWhatWeSaved(){
        // Arrange
        Team team = new Team("Kom Hamada");
        //Act

        this.teamRepository.save(team);
        this.teamRepository.deleteById(team.getID());


        // Assert
        Assertions.assertFalse(teamRepository.existsById(team.getID()));

    }
    @Test
    public void TeamRepository_BatchSaveThenBatchDeleteCheckSize_FindWhatWeSaved(){
        // Arrange
        String s = "";
        for(int i = 0; i < 100; i++){
            s+="a";
            Team team = new Team(s);
            // Act
            this.teamRepository.save(team);
        }
        //Act

        this.teamRepository.deleteAll();


        // Assert
        Assertions.assertEquals(0, teamRepository.findAll().size());

    }




}

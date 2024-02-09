package com.fantasy.fantasyleague.Group.Repository;

import com.fantasy.fantasyleague.Group.Model.GroupFantasy;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class GroupRepositoryTests {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    private User owner;
    private List<User> users;
    private User savedOwner;

    @BeforeEach
    public void fun() {
        owner = new User();
        users = new ArrayList<>();
        owner.setFirstName("mohamed1");
        owner.setLastName("arous1");
        owner.setEmail("mohamed.arous9401@gmail.com");
        owner.setPassword("12259861");
        owner.setUserName("mohamed_arous1");
        userRepository.save(owner);
        User mohamed1 = new User();
        mohamed1.setFirstName("mohamed1");
        mohamed1.setLastName("arous1");
        mohamed1.setEmail("mohamedarous9401@gmail.com");
        mohamed1.setPassword("12259861");
        mohamed1.setUserName("Mohamed_arous1");
        userRepository.save(mohamed1);
    }

    @Test
    public void GroupRepository_SaveThenFindByName_ReturnNotNull() {
        // Arrange
        GroupFantasy groupFantasy = new GroupFantasy(1, "Champions", 1, userRepository.findByUserName("mohamed_arous1").orElseThrow(), new ArrayList<>());
        //Act
        this.groupRepository.save(groupFantasy);
        //Assert
        Assertions.assertNotNull(this.groupRepository.findByName(groupFantasy.getName()));
    }

    @Test
    public void GroupRepository_DoNotSaveThenFindByName_ReturnNull() {
        // Arrange
        GroupFantasy groupFantasy = new GroupFantasy(1, "Champions", 1, userRepository.findByUserName("mohamed_arous1").orElseThrow(), new ArrayList<>());
        //Act
        //Assert
        Assertions.assertNotNull(this.groupRepository.findByName(groupFantasy.getName()));
    }

    @Test
    public void TeamRepository_findPublicGroupsNotContainingUser_ReturnNoGroup() {
        // Arrange
        List<User> list = new ArrayList<>();
        list.add(userRepository.findByUserName("mohamed_arous1").orElseThrow());
        list.add(userRepository.findByUserName("Mohamed_arous1").orElseThrow());
        GroupFantasy groupFantasy = new GroupFantasy(1, "Champions", 1, userRepository.findByUserName("mohamed_arous1").orElseThrow(), list);
        //Act
        groupRepository.save(groupFantasy);
        //Assert
        Assertions.assertEquals(0, this.groupRepository.findPublicGroupsNotContainingUser("mohamed_arous1").size());
    }

    @Test
    public void TeamRepository_findPublicGroupsNotContainingUser_ReturnOneGroup() {
        // Arrange
        List<User> list = new ArrayList<>();
        list.add(userRepository.findByUserName("Mohamed_arous1").orElseThrow());
        GroupFantasy groupFantasy = new GroupFantasy(1, "Champions", 1, userRepository.findByUserName("mohamed_arous1").orElseThrow(), list);
        //Act
        groupRepository.save(groupFantasy);
        //Assert
        Assertions.assertEquals(0, this.groupRepository.findPublicGroupsNotContainingUser("mohamed_arous1").size());
    }

}
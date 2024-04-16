//package com.fantasy.fantasyleague.RealLeague.DTOs;
//
////import com.fantasy.fantasyleague.Group.DTO.*;
////import com.fantasy.fantasyleague.Group.Model.GroupFantasy;
//import com.fantasy.fantasyleague.Registiration.Model.User;
//import org.junit.jupiter.api.Test;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class AllDTOTests {
//
//    @Test
//    public void testGroupCreatorDTO() {
//        // Arrange
//        String groupName = "TestGroup";
//        int isPrivate = 1;
//        String userName = "TestUser";
//
//        // Act
//        GroupCreatorDTO groupCreatorDTO = new GroupCreatorDTO(groupName, isPrivate, userName);
//
//        // Assert
//        assertThat(groupCreatorDTO.getGroupName()).isEqualTo(groupName);
//        assertThat(groupCreatorDTO.getIsPrivate()).isEqualTo(isPrivate);
//        assertThat(groupCreatorDTO.getUserName()).isEqualTo(userName);
//    }
//
//    @Test
//    public void testGroupDTO() {
//        // Arrange
//        GroupFantasy groupFantasy = new GroupFantasy();
//        groupFantasy.setName("TestGroup");
//        groupFantasy.setGroupID(1);
//        User user = new User();
//        user.setUserName("Gog");
//        groupFantasy.setOwner(user);
//
//        List<User> users = Arrays.asList(new User(), new User());
//        groupFantasy.setUsers(users);
//
//        // Act
//        GroupDTO groupDTO = new GroupDTO(groupFantasy);
//
//        // Assert
//        assertThat(groupDTO.getGroupName()).isEqualTo("TestGroup");
//        assertThat(groupDTO.getGroupID()).isEqualTo(1);
//        assertThat(groupDTO.getOwnerName()).isEqualTo("Gog");
//        assertThat(groupDTO.getNoParticipants()).isEqualTo(2);
//        assertThat(groupDTO.getAvgPoints()).isEqualTo(0.0);
//    }
//
//    @Test
//    public void testGroupInfoDTO() {
//        // Arrange
//        List<UserGroupDTO> userGroupDTOList = Arrays.asList(new UserGroupDTO(), new UserGroupDTO());
//        int rankInGroup = 1;
//
//        // Act
//        GroupInfoDTO groupInfoDTO = new GroupInfoDTO(userGroupDTOList, rankInGroup);
//
//        // Assert
//        assertThat(groupInfoDTO.getGroupDTOList()).isEqualTo(userGroupDTOList);
//        assertThat(groupInfoDTO.getRankInGroup()).isEqualTo(rankInGroup);
//    }
//
//    @Test
//    public void testUserGroupDTO() {
//        // Arrange
//        User user = new User();
//        user.setFirstName("John");
//        user.setLastName("Doe");
//        user.setUserName("john.doe");
//        user.setRegion("US");
//        user.setPoints(100);
//
//        // Act
//        UserGroupDTO userGroupDTO = new UserGroupDTO(user);
//
//        // Assert
//        assertThat(userGroupDTO.getFirstName()).isEqualTo("John");
//        assertThat(userGroupDTO.getLastName()).isEqualTo("Doe");
//        assertThat(userGroupDTO.getUserName()).isEqualTo("john.doe");
//        assertThat(userGroupDTO.getRegion()).isEqualTo("US");
//        assertThat(userGroupDTO.getOverallPoints()).isEqualTo(100);
//    }
//
//    @Test
//    public void testUserToGroupAdderDTO() {
//        // Arrange
//        String userName = "testUser";
//        int groupID = 1;
//
//        // Act
//        UserToGroupAdderDTO userToGroupAdderDTO = new UserToGroupAdderDTO(userName, groupID);
//
//        // Assert
//        assertThat(userToGroupAdderDTO.getUserName()).isEqualTo(userName);
//        assertThat(userToGroupAdderDTO.getGroupID()).isEqualTo(groupID);
//    }
//}

package com.fantasy.fantasyleague.Group.Service;

import com.fantasy.fantasyleague.Group.DTO.GroupCreatorDTO;
import com.fantasy.fantasyleague.Group.DTO.UserToGroupAdderDTO;
import com.fantasy.fantasyleague.Group.Model.GroupFantasy;
import com.fantasy.fantasyleague.Group.Repository.GroupRepository;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTests {
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private GroupServiceImpl groupService;

    @Test
    public void GroupService_insertGroupSuccessfullyPrivateGroup_ReturnResponseEntity200AndID() {
        User user = new User();
        user.setUserName("Ahmed Abdul Aziz");
        when(userRepository.findByUserName(anyString())).thenReturn(user);
        Optional<GroupFantasy> empty = Optional.empty();
        GroupCreatorDTO groupCreatorDTO = new GroupCreatorDTO("El Ahly", 1, user.getUserName());
        GroupFantasy groupFantasy = new GroupFantasy();
        when(groupRepository.findByName(anyString())).thenReturn(empty);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(groupRepository.save(any(GroupFantasy.class))).thenReturn(groupFantasy);
        ResponseEntity<Map<String, String>> response = groupService.createGroup(groupCreatorDTO);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }
    @Test
    public void GroupService_insertGroupUnsuccessfullyOwnerDoesNotExist_ReturnResponseEntity200() {
        User user = new User();
        when(userRepository.findByUserName(anyString())).thenReturn(null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, groupService.createGroup(new GroupCreatorDTO()).getStatusCode());
    }
    @Test
    public void GroupService_insertGroupUnsuccessfullyGroupSAMENAMEOFNAME_ReturnResponseEntity400() {
        User user = new User();
        user.setUserName("Ahmed Abdul Aziz");
        when(userRepository.findByUserName(anyString())).thenReturn(user);
        GroupFantasy groupFantasy = new GroupFantasy();
        Optional<GroupFantasy> empty = Optional.of(groupFantasy);
        GroupCreatorDTO groupCreatorDTO = new GroupCreatorDTO("El Ahly", 1, user.getUserName());
        when(groupRepository.findByName(anyString())).thenReturn(empty);
        ResponseEntity<Map<String, String>> response = groupService.createGroup(groupCreatorDTO);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    public void GroupService_insertGroupSuccessfullyPublic_ReturnResponseEntity200Only() {
        User user = new User();
        user.setUserName("Ahmed Abdul Aziz");
        when(userRepository.findByUserName(anyString())).thenReturn(user);
        Optional<GroupFantasy> empty = Optional.empty();
        GroupCreatorDTO groupCreatorDTO = new GroupCreatorDTO("El Ahly", 0, user.getUserName());
        GroupFantasy groupFantasy = new GroupFantasy();
        when(groupRepository.findByName(anyString())).thenReturn(empty);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(groupRepository.save(any(GroupFantasy.class))).thenReturn(groupFantasy);
        ResponseEntity<Map<String, String>> response = groupService.createGroup(groupCreatorDTO);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(0, Objects.requireNonNull(response.getBody()).size());
    }
    @Test
    public void GroupService_addUserToGroupUnsuccessfullyNotAvailableUser_Return400(){
        when(userRepository.findByUserName("Khalid")).thenReturn(null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, groupService.addUserToGroup(new UserToGroupAdderDTO()).getStatusCode());
    }
    @Test
    public void GroupService_addUserToGroupUnsuccessfullyNotAvailableGroup_return400(){
        User user = new User();
        user.setUserName("Ahmed Abdul Aziz");
        when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
        when(groupRepository.getReferenceById(1)).thenReturn(null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, groupService.addUserToGroup(new UserToGroupAdderDTO("Ahmed Abdul Aziz", 1)).getStatusCode());
    }
    @Test
    public void GroupService_addUserToGroupUnsuccessfullyUserAlreayExist_return400(){
        User user = new User();
        user.setUserName("Ahmed Abdul Aziz");
        List<User> groupUsers = new ArrayList<>();
        groupUsers.add(user);
        GroupFantasy groupFantasy = new GroupFantasy();
        groupFantasy.setUsers(groupUsers);
        UserToGroupAdderDTO userToGroupAdderDTO = new UserToGroupAdderDTO();
        userToGroupAdderDTO.setUserName(user.getUserName());
        userToGroupAdderDTO.setGroupID(1);
        when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
        when(groupRepository.getReferenceById(1)).thenReturn(groupFantasy);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, groupService.addUserToGroup(userToGroupAdderDTO).getStatusCode());
    }
    @Test
    public void GroupService_addUserToGroupSuccessfullyUserAlreadyExist_return200(){
        User user = new User();
        user.setUserName("Ahmed Abdul Aziz");
        List<User> groupUsers = new ArrayList<>();
      //  groupUsers.add(user);
        GroupFantasy groupFantasy = new GroupFantasy();
        groupFantasy.setUsers(groupUsers);
        UserToGroupAdderDTO userToGroupAdderDTO = new UserToGroupAdderDTO();
        userToGroupAdderDTO.setUserName(user.getUserName());
        userToGroupAdderDTO.setGroupID(1);
        when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
        when(groupRepository.getReferenceById(1)).thenReturn(groupFantasy);
        when(userRepository.save(user)).thenReturn(user);
        when(groupRepository.save(groupFantasy)).thenReturn(groupFantasy);
        Assertions.assertEquals(HttpStatus.OK, groupService.addUserToGroup(userToGroupAdderDTO).getStatusCode());
    }

    @Test
    public void GroupService_getAll_returnList(){
        List<GroupFantasy> list = new ArrayList<>();
        when(groupRepository.findAll()).thenReturn(list);
        Assertions.assertEquals(list, groupService.getAll());
    }
    @Test
    public void GroupService_getGroupsOfUser_returnListOfGroups(){
        List<GroupFantasy> L = new ArrayList<>();
        GroupFantasy groupFantasy = new GroupFantasy(), groupFantasy1 = new GroupFantasy();
        groupFantasy1.setOwner(new User()); groupFantasy.setOwner(new User());
        L.add(groupFantasy1);
        L.add(groupFantasy);
        User user = new User();
        user.setGroupFantasies(L);
        when(userRepository.findByUserName("Yousef")).thenReturn(user);
        Assertions.assertEquals(2, groupService.getGroupsOfUser("Yousef").size());
    }
    @Test
    public void GroupService_getSpecificUserInfoUnsuccessfully_returnGroupInfoDTO(){
        List<GroupFantasy> L = new ArrayList<>();
        GroupFantasy groupFantasy = new GroupFantasy(), groupFantasy1 = new GroupFantasy();
        groupFantasy1.setOwner(new User()); groupFantasy.setOwner(new User());
        L.add(groupFantasy1);
        L.add(groupFantasy);
        User user = new User();
        user.setGroupFantasies(L);
        when(userRepository.findByUserName("Yousef")).thenReturn(user);
        Assertions.assertEquals(2, groupService.getGroupsOfUser("Yousef").size());
    }
    @Test
    public void GroupService_getSpecificUserInfoSuccessfully_returnGroupInfoDTO(){
        List<GroupFantasy> L = new ArrayList<>();
        GroupFantasy groupFantasy = new GroupFantasy(), groupFantasy1 = new GroupFantasy();
        groupFantasy1.setOwner(new User()); groupFantasy.setOwner(new User());
        L.add(groupFantasy1);
        L.add(groupFantasy);
        User user = new User();
        user.setGroupFantasies(L);
        when(userRepository.findByUserName("Yousef")).thenReturn(user);
        Assertions.assertEquals(2, groupService.getGroupsOfUser("Yousef").size());
    }
    @Test
    public void GroupService_getSpecificGroupInfo_return400(){
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, groupService.getSpecificGroupInfo("1a76575", "").getStatusCode());
    }
    @Test
    public void GroupService_getSpecificGroupInfo_return200AndGroupInfoDTO(){
        GroupFantasy groupFantasy = new GroupFantasy();
        groupFantasy.setOwner(new User());
        groupFantasy.setName("Yusuf");
        groupFantasy.setGroupID(1);
        groupFantasy.setIsPrivate(1|0); // no difference
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setUserName("Abdo");
        User user1 = new User();
        user1.setUserName("Amor");
        list.add(user1); list.add(user);
        groupFantasy.setUsers(list);
        when(groupRepository.getReferenceById(anyInt())).thenReturn(groupFantasy);
        Assertions.assertEquals(HttpStatus.OK, groupService.getSpecificGroupInfo("1","Amor").getStatusCode());
        Assertions.assertEquals("Amor", groupService.getSpecificGroupInfo("1","Amor").getBody().getGroupDTOList().get(0).getUserName());
    }
    @Test
    public void groupService_getPublicGroups_returnAGroup(){
        User user = new User();
        user.setUserName("Eyad");
        when(userRepository.findByUserName("Eyad")).thenReturn(user);
        List<GroupFantasy> groupFantasies = new ArrayList<>();
        GroupFantasy groupFantasy = new GroupFantasy();
        groupFantasy.setOwner(new User());
        groupFantasy.setName("Yusuf");
        groupFantasy.setGroupID(1);
        groupFantasy.setIsPrivate(1); // no difference
        List<GroupFantasy> list = new ArrayList<>();

        list.add(groupFantasy);
        when(groupRepository.findPublicGroupsNotContainingUser("Eyad")).thenReturn(list);
        Assertions.assertEquals(1, groupService.getPublicGroups("Eyad").size());
    }
//    @Test
//    public void GroupService_InsertGroupUnsuccessfullyTwoGroupsWithSameName_ReturnResponseEntity200() {
//        User user = new User();
//        when(userRepository.findByUserName(anyString())).thenReturn(user);
//        when(userRepository.findByUserName().thenReturn(user);
//        Assertions.assertEquals(HttpStatus.BAD_REQUEST, groupService.createGroup(new GroupCreatorDTO()).getStatusCode());
//    }
//    @Test
//    public void TeamService_InsertTeamUnsuccessfullyAsTeamExists_ReturnResponseEntity400() {
//        when(teamRepository.findByName(anyString())).thenReturn(new Team());
//        ResponseEntity response = teamService.insertTeam("Damanhour");
//        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//    }
//
//    @Test
//    public void TeamService_UpdateTeamSuccessfully_ReturnResponseEntity200() {
//        Team team = new Team("Damanhour");
//        Team teamToUpdate = new Team("Somouha");
//
//        when(teamRepository.existsById(anyInt())).thenReturn(true);
//        when(teamRepository.getReferenceById(anyInt())).thenReturn(team);
//        when(teamRepository.save(Mockito.any(Team.class))).thenReturn(teamToUpdate);
//
//        ResponseEntity response = teamService.updateTeam(String.valueOf(anyInt()), "Somouha");
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//    @Test
//    public void TeamService_UpdateTeamUnsuccessfullyAsIDDoesntExist_ReturnResponseEntity404() {
//        Team team = new Team("Damanhour");
//
//        when(teamRepository.existsById(team.getID())).thenReturn(false);
//        ResponseEntity response = teamService.updateTeam(String.valueOf(team.getID()), "Somouha");
//
//        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    public void TeamService_UpdateTeamNotGivingAnIntegerString_ReturnResponseEntity500() {
//
//        ResponseEntity response = teamService.updateTeam("NaN", "Somouha");
//
//        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//
//    @Test
//    public void TeamService_DeleteTeamSuccessfully_Returns200() {
//
//        when(teamRepository.existsById(anyInt())).thenReturn(true);
//        doNothing().when(teamRepository).deleteById(anyInt());
//
//        ResponseEntity response = teamService.deleteTeam(String.valueOf(anyInt()));
//
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//
//    }
//
//    @Test
//    public void TeamService_DeleteTeamUnsuccessfully_ReturnsNotFound404() {
//
//
//        when(teamRepository.existsById(anyInt())).thenReturn(false);
//
//        ResponseEntity response = teamService.deleteTeam(String.valueOf(anyInt()));
//
//        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    public void TeamService_DeleteTeamUnsuccessfully_ReturnsInternalServerError500() {
//
//
//        ResponseEntity response = teamService.deleteTeam("NaN");
//        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//
//    }
//
//    @Test
//    public void TeamService_GetAllTeam_ReturnsListOfTeams() {
//        Team team = new Team("Alahly");
//        Team team1 = new Team("AlZamalek");
//        List<Team> teamList = new ArrayList<>();
//        teamList.add(team1);
//        teamList.add(team);
//
//
//        when(teamRepository.findAll()).thenReturn(teamList);
//
//
//        Assertions.assertEquals(teamList, teamService.getAllTeams());
//
//    }
//
//    @Test
//    public void TeamService_DeleteAllTeams_Returns200() {
//
//        doNothing().when(teamRepository).deleteAll();
//
//        ResponseEntity response = teamService.deleteAllTeam();
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//
//    }
}


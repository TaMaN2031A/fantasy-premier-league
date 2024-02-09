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
        when(userRepository.findByEmailOrUserName(anyString(), anyString())).thenReturn(user);
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
        when(userRepository.findByEmailOrUserName(anyString(), anyString())).thenReturn(null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, groupService.createGroup(new GroupCreatorDTO("ll",1,"l")).getStatusCode());
    }
    @Test
    public void GroupService_insertGroupUnsuccessfullyGroupSAMENAMEOFNAME_ReturnResponseEntity400() {
        User user = new User();
        user.setUserName("Ahmed Abdul Aziz");
        when(userRepository.findByEmailOrUserName(anyString(), anyString())).thenReturn(user);
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
        when(userRepository.findByEmailOrUserName(anyString(), anyString())).thenReturn(user);
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
        when(userRepository.findByEmailOrUserName("Khalid", "Khalid")).thenReturn(null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, groupService.addUserToGroup(new UserToGroupAdderDTO()).getStatusCode());
    }
    @Test
    public void GroupService_addUserToGroupUnsuccessfullyNotAvailableGroup_return400(){
        User user = new User();
        user.setUserName("Ahmed Abdul Aziz");
        when(userRepository.findByEmailOrUserName(user.getUserName(), user.getUserName())).thenReturn(user);
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
        when(userRepository.findByEmailOrUserName(user.getUserName(), user.getUserName())).thenReturn(user);
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
        when(userRepository.findByEmailOrUserName(user.getUserName(), user.getUserName())).thenReturn(user);
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
        when(userRepository.findByEmailOrUserName("Yousef", "Yousef")).thenReturn(user);
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
        when(userRepository.findByEmailOrUserName("Yousef", "Yousef")).thenReturn(user);
        Assertions.assertEquals(2, groupService.getGroupsOfUser("Yousef").size());
    }
    @Test
    public void GroupService_getSpecificUserInfoSuccessfully_returnList(){
        List<GroupFantasy> L = new ArrayList<>();
        GroupFantasy groupFantasy = new GroupFantasy(), groupFantasy1 = new GroupFantasy();
        groupFantasy1.setOwner(new User()); groupFantasy.setOwner(new User());
        L.add(groupFantasy1);
        L.add(groupFantasy);
        User user = new User();
        user.setGroupFantasies(L);
        when(userRepository.findByEmailOrUserName("Yousef", "Yousef")).thenReturn(user);
        Assertions.assertEquals(2, groupService.getGroupsOfUser("Yousef").size());
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
        Assertions.assertEquals("Amor", groupService.getSpecificGroupInfo("1","Amor").get(0).getUserName());
    }
    @Test
    public void groupService_getPublicGroups_returnAGroup(){
        User user = new User();
        user.setUserName("Eyad");
        when(userRepository.findByEmailOrUserName("Eyad", "Eyad")).thenReturn(user);
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
}


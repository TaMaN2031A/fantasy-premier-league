package com.fantasy.fantasyleague.Group.Controller;

import com.fantasy.fantasyleague.Group.DTO.GroupCreatorDTO;
import com.fantasy.fantasyleague.Group.DTO.GroupDTO;
import com.fantasy.fantasyleague.Group.DTO.UserGroupDTO;
import com.fantasy.fantasyleague.Group.DTO.UserToGroupAdderDTO;
import com.fantasy.fantasyleague.Group.Model.GroupFantasy;
import com.fantasy.fantasyleague.Group.Service.GroupServiceImpl;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GroupController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupServiceImpl groupService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getUserGroups_ReturnsUserGroups() throws Exception {
        // Arrange
        String userName = "testUser";
        List<GroupDTO> groups = Arrays.asList(new GroupDTO(), new GroupDTO());
        when(groupService.getGroupsOfUser(userName)).thenReturn(groups);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/group/getUserGroups/{userName}", userName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(groups.size())));
    }

    @Test
    public void getPublicGroups_ReturnsPublicGroups() throws Exception {
        // Arrange
        String userName = "testUser";
        List<GroupDTO> publicGroups = Arrays.asList(new GroupDTO(), new GroupDTO());
        when(groupService.getPublicGroups(userName)).thenReturn(publicGroups);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/group/getPublicGroups/{userName}", userName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(publicGroups.size())));
    }

    @Test
    public void getSpecificGroupInfo_ReturnsGroupInfo() throws Exception {
        // Arrange
        String groupID = "1";
        String userName = "testUser";

        User user = new User();
        user.setUserName(userName);
        user.setPoints(100);
        user.setRegion("North");
        user.setFirstName("John");
        user.setLastName("Doe");
        UserGroupDTO expectedUserGroupDTO = new UserGroupDTO(user);

        List<UserGroupDTO> userList = Collections.singletonList(expectedUserGroupDTO);

        when(groupService.getSpecificGroupInfo(groupID, userName)).thenReturn(userList);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/group/getSpecificGroupInfo/{groupID}/{userName}", groupID, userName))
                .andExpect(jsonPath("$[0].userName", Matchers.is("testUser")))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("John")))
                .andExpect(jsonPath("$[0].lastName", Matchers.is("Doe")))
                .andExpect(jsonPath("$[0].region", Matchers.is("North")))
                .andExpect(jsonPath("$[0].overallPoints", Matchers.is(100)));
    }

    @Test
    public void getAll_ReturnsAllGroups() throws Exception {
        // Arrange
        List<GroupFantasy> allGroups = Arrays.asList(new GroupFantasy(), new GroupFantasy());
        when(groupService.getAll()).thenReturn(allGroups);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/group/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(allGroups.size())));
    }

    @Test
    public void createGroup_ReturnsResponseEntity() throws Exception {
        // Arrange
        GroupCreatorDTO groupCreatorDTO = new GroupCreatorDTO();
        ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.ok(Collections.singletonMap("Id", "7948"));
        when(groupService.createGroup(groupCreatorDTO)).thenReturn(responseEntity);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/group/createGroup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupCreatorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Id").value("7948"));
    }

    @Test
    public void addUserToGroup_ReturnsResponseEntity() throws Exception {
        // Arrange
        UserToGroupAdderDTO userToGroupAdderDTO = new UserToGroupAdderDTO();
        ResponseEntity<Map<String, String>> responseEntity = ResponseEntity.ok(Collections.singletonMap("key", "value"));
        when(groupService.addUserToGroup(userToGroupAdderDTO)).thenReturn(responseEntity);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/group/addUserToGroup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userToGroupAdderDTO)))
                .andExpect(status().isOk());
    }

}

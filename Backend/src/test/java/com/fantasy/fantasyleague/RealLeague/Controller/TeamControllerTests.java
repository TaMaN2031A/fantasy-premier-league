package com.fantasy.fantasyleague.RealLeague.Controller;

import com.fantasy.fantasyleague.RealLeague.DTO.TeamDTO;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TeamController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class TeamControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TeamService teamService;

    @Autowired
    private ObjectMapper objectMapper;

    private String teamName;
    private String validId;
    private String notValidId;
    private Team team;
    private Team team2;
    private List<Team> list = new ArrayList<>();

    @BeforeEach
    public void init(){
        teamName = "Al Ahly";
        validId = "101";
        notValidId = "AnyStringWithCharacters";
        team = new Team("Al Ahly");
        team2 = new Team("Al Zamalek");
        list.add(team); list.add(team2);
    }

    @Test
    public void TeamController_AddTeamWhenNewTeam_Return200() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("message", "Team inserted successfully");

        // Mock the behavior of teamService.insertTeam
        given(teamService.insertTeam(ArgumentMatchers.any())).
                willReturn(ResponseEntity.ok(responseS));

        // Perform the POST request
        ResultActions response = mockMvc.perform(
                post("/team/insert")
                        .contentType("application/json")
                        .content(
                                objectMapper.writeValueAsString(TeamDTO.builder().name(teamName).build())
                        )
        );

        // Check the HTTP status code
        response.andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Team inserted successfully"));
    }

    @Test
    public void TeamController_AddTeamWhenTeamExists_Return400() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("error", "Team Already Exists");
        // Mock the behavior of teamService.insertTeam
        given(teamService.insertTeam(ArgumentMatchers.any()))
                .willReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(responseS));

        // Perform the POST request
        ResultActions response = mockMvc.perform(
                post("/team/insert")
                    .contentType("application/json")
                    .content(
                            objectMapper.writeValueAsString(TeamDTO.builder().name(teamName).build())
                    )
        );

        // Check the HTTP status code
        response.andExpect(status().isBadRequest())
                .andExpect(jsonPath("error").value("Team Already Exists"));

    }

    @Test
    public void TeamController_DeleteTeamWhenTeamExists_Return200() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("message", "Team deleted successfully");

        // Mock the behavior of teamService.insertTeam
        given(teamService.deleteTeam(ArgumentMatchers.any())).willReturn(ResponseEntity.ok(responseS));

        // Perform the POST request
        ResultActions response = mockMvc.perform(delete("/team/delete/{id}", validId));

        // Check the HTTP status code
        response.andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Team deleted successfully"));


    }

    @Test
    public void TeamController_DeleteTeamWhenTeamDoesNotExists_Return404() throws Exception {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error", "Team doesn't exist");
        // Mock the behavior of teamService.insertTeam
        given(teamService.deleteTeam(ArgumentMatchers.any())).willReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap));

        // Perform the POST request
        ResultActions response = mockMvc.perform(delete("/team/delete/{id}", validId));

        // Check the HTTP status code
        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("error").value("Team doesn't exist"));

    }

    @Test
    public void TeamController_DeleteTeamWhenSendsCharactersInID_Return500() throws Exception {

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error", "java.lang.NumberFormatException: For input string: "+notValidId);
        // Mock the behavior of teamService.insertTeam
        given(teamService.deleteTeam(ArgumentMatchers.any())).willReturn(ResponseEntity.internalServerError().body(responseMap));

        // Perform the POST request
        ResultActions response = mockMvc.perform(delete("/team/delete/{id}", notValidId));

        // Check the HTTP status code
        response.andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("java.lang.NumberFormatException: For input string: " + notValidId));

    }

    @Test
    public void TeamController_GetAll_returnsListOfTeams() throws Exception {

        given(teamService.getAllTeams()).willReturn(list);

        ResultActions response = mockMvc.perform(get("/team/getAll"));

        // Check the HTTP status code
        response.andExpect(status().isOk())

                // Check the JSON structure and values
                .andExpect(jsonPath("$", hasSize(2))) // Assuming the list has two teams
                .andExpect(jsonPath("$[0].name", is("Al Ahly")))
                .andExpect(jsonPath("$[1].name", is("Al Zamalek")));
    }

    @Test
    public void TeamController_DeleteAll_returnsListOfTeams() throws Exception {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "All Teams deleted successfully");

        given(teamService.deleteAllTeam()).willReturn(ResponseEntity.ok(responseMap));

        ResultActions response = mockMvc.perform(delete("/team/deleteAll"));

        // Check the HTTP status code
        response.andExpect(status().isOk())

                // Check the JSON structure and values
                .andExpect(jsonPath("message").value("All Teams deleted successfully"));
    }

    @Test
    public void TeamController_UpdateTeam_Return200() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("message", "Team Updated successfully");

        // Mock the behavior of teamService.insertTeam
        given(teamService.updateTeam(ArgumentMatchers.any(), ArgumentMatchers.any())).
                willReturn(ResponseEntity.ok(responseS));

        // Perform the POST request
        ResultActions response = mockMvc.perform(post("/team/update/{id}/{name}", validId, teamName));

        // Check the HTTP status code
        response.andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Team Updated successfully"));

    }

    @Test
    public void TeamController_UpdateTeam_Return404() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("error", "Team doesn't exist");

        // Mock the behavior of teamService.insertTeam
        given(teamService.updateTeam(ArgumentMatchers.any(), ArgumentMatchers.any())).
                willReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseS));

        // Perform the POST request
        ResultActions response = mockMvc.perform(post("/team/update/{id}/{name}", validId, teamName));

        // Check the HTTP status code
        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("error").value("Team doesn't exist"));

    }

    @Test
    public void TeamController_UpdateTeam_Return500() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("error", "java.lang.NumberFormatException: For input string: "+notValidId);

        // Mock the behavior of teamService.insertTeam
        given(teamService.updateTeam(ArgumentMatchers.any(), ArgumentMatchers.any())).
                willReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseS));

        // Perform the POST request
        ResultActions response = mockMvc.perform(post("/team/update/{id}/{name}", notValidId, teamName));

        // Check the HTTP status code
        response.andExpect(status().isInternalServerError())
                .andExpect(jsonPath("error").value("java.lang.NumberFormatException: For input string: "+notValidId));

    }
}

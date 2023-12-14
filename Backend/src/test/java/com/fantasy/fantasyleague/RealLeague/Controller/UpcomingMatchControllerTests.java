package com.fantasy.fantasyleague.RealLeague.Controller;

import com.fantasy.fantasyleague.RealLeague.DTO.PlayerDTO;
import com.fantasy.fantasyleague.RealLeague.DTO.UpcomingMatchInsertionDTO;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Team;
import com.fantasy.fantasyleague.RealLeague.Model.UpcomingMatch;
import com.fantasy.fantasyleague.RealLeague.Service.PlayerService;
import com.fantasy.fantasyleague.RealLeague.Service.UpcomingMatchService;
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
import org.springframework.http.MediaType;
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
@WebMvcTest(controllers = UpcomingMatchController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UpcomingMatchControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UpcomingMatchService upcomingMatchService;

    @Autowired
    private ObjectMapper objectMapper;

    private String validId;
    private String notValidId;
    private UpcomingMatch upcomingMatch;
    private UpcomingMatch upcomingMatch2;
    List<UpcomingMatch> list;
    private UpcomingMatchInsertionDTO upcomingMatchInsertionDTO;

    @BeforeEach
    public void init(){
        upcomingMatchInsertionDTO = new UpcomingMatchInsertionDTO(1, 1, 2, "Bernabeua");
        validId = "101";
        notValidId = "Characters destroy";
        list = new ArrayList<>();
        upcomingMatch = new UpcomingMatch(new Team("Al Ahly"), new Team("El Zamalek"), 1, "El Salam");
        upcomingMatch2 = new UpcomingMatch(new Team("Al Itthiad"), new Team("El Ismaily"), 1, "El Salam");
        list.add(upcomingMatch); list.add(upcomingMatch2);
    }

    @Test
    public void UpcomingController_AddUpcomingMatch_Return200() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("message", "Upcoming match inserted successfully");

        // Mocking the Service
        given(upcomingMatchService.insertUpComingMatch(ArgumentMatchers.any())).
                willReturn(ResponseEntity.ok(responseS));


        ResultActions response = mockMvc.perform(post("/upcomingMatch/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(upcomingMatchInsertionDTO)));


        // Check the HTTP status code
        response.andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Upcoming match inserted successfully"));

    }

    @Test
    public void PlayerController_AddUpcomingMatchWhenWrongWeekNumberExists_Return400() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("error", "Wrong week number");
        // Mocking the Service
        given(upcomingMatchService.insertUpComingMatch(ArgumentMatchers.any()))
                .willReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(responseS));


        ResultActions response = mockMvc.perform(post("/upcomingMatch/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(upcomingMatchInsertionDTO)));
        // Check the HTTP status code
        response.andExpect(status().isBadRequest())
                .andExpect(jsonPath("error").value("Wrong week number"));

    }

    @Test
    public void PlayerController_AddTeamWhenInvalidShirtNumber_Return400() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("error", "Teams doesn't exist");
        // Mocking the Service
        given(upcomingMatchService.insertUpComingMatch(ArgumentMatchers.any()))
                .willReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(responseS));


        ResultActions response = mockMvc.perform(post("/upcomingMatch/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(upcomingMatchInsertionDTO)));
        // Check the HTTP status code
        response.andExpect(status().isBadRequest())
                .andExpect(jsonPath("error")
                        .value("Teams doesn't exist"));

    }

    @Test
    public void PlayerController_DeletePlayerWhenPlayerExists_Return200() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("message", "Upcoming match deleted successfully");

        // Mocking the Service
        given(upcomingMatchService.deleteUpcomingMatch(ArgumentMatchers.any()))
                .willReturn(ResponseEntity.status(HttpStatus.OK).body(responseS));


        ResultActions response = mockMvc
                .perform(delete("/upcomingMatch/delete/{id}", validId));

        // Check the HTTP status code
        response.andExpect(status().isOk())
                .andExpect(jsonPath("message").
                        value("Upcoming match deleted successfully"));
    }

    @Test
    public void PlayerController_DeletePlayerWhenPlayerDoesNotExists_Return404() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("message", "Upcoming match ID doesn't exist");

        // Mocking the Service
        given(upcomingMatchService.deleteUpcomingMatch(ArgumentMatchers.any()))
                .willReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseS));


        ResultActions response = mockMvc
                .perform(delete("/upcomingMatch/delete/{id}", validId));

        // Check the HTTP status code
        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("message").
                        value("Upcoming match ID doesn't exist"));

    }

    @Test
    public void PlayerController_DeletePlayerWhenSendsCharactersInID_Return500() throws Exception {

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error", "java.lang.NumberFormatException: For input string: "+notValidId);
        // Mocking the Service
        given(upcomingMatchService.deleteUpcomingMatch(ArgumentMatchers.any()))
                .willReturn(ResponseEntity.internalServerError()
                        .body(responseMap));


        ResultActions response = mockMvc.
                perform(delete("/upcomingMatch/delete/{id}", notValidId));

        // Check the HTTP status code
        response.andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("java.lang.NumberFormatException: For input string: " + notValidId));

    }

    @Test
    public void PlayerController_GetAll_returnsListOfTeams() throws Exception {

        given(upcomingMatchService.getAllUpcomingMatch()).willReturn(list);

        ResultActions response = mockMvc.perform(get("/upcomingMatch/getAll"));

        // Check the HTTP status code
        response.andExpect(status().isOk())

                // Check the JSON structure and values
                .andExpect(jsonPath("$", hasSize(2))) // Assuming the list has two teams
                .andExpect(jsonPath("$[0].id", is(upcomingMatch.getID())))
                .andExpect(jsonPath("$[1].id", is(upcomingMatch2.getID())))
        ;
    }
//
    @Test
    public void PlayerController_DeleteAll_returnsListOfTeams() throws Exception {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "All Upcoming matches deleted successfully");

        given(upcomingMatchService.deleteAllUpcomingMatch()).willReturn(ResponseEntity.ok(responseMap));

        ResultActions response = mockMvc.perform(delete("/upcomingMatch/deleteAll"));

        // Check the HTTP status code
        response.andExpect(status().isOk())

                // Check the JSON structure and values
                .andExpect(jsonPath("message")
                        .value("All Upcoming matches deleted successfully"));
    }

}

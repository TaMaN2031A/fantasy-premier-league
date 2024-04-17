package com.fantasy.fantasyleague.RealLeague.Controller;

import com.fantasy.fantasyleague.RealLeague.DTO.PlayerDTO;
import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.RealLeague.Model.Position;
import com.fantasy.fantasyleague.RealLeague.Service.PlayerService;
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
@WebMvcTest(controllers = PlayerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PlayerControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlayerService playerService;

    @Autowired
    private ObjectMapper objectMapper;

    private String validId;
    private String notValidId;
    private Player player;
    private Player player2;
    private PlayerDTO playerDTO;
    private List<Player> list = new ArrayList<>();

    @BeforeEach
    public void init(){
        validId = "101";
        notValidId = "AnyStringWithCharacters";
        player = new Player("Eyad Games", Position.MID.name(), 21, 1);
        player2 = new Player("Abo Treika", Position.MID.name(), 22, 1);
        playerDTO = PlayerDTO.builder().name("Eyad Games").position(Position.MID.name()).number_in_team(21).id_of_team(1).build();
        list.add(player); list.add(player2);
    }

    @Test
    public void PlayerController_AddPlayer_Return200() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("message", "Player inserted successfully");

        // Mocking the Service
        given(playerService.insertPlayer(ArgumentMatchers.any())).
                willReturn(ResponseEntity.ok(responseS));

        
        ResultActions response = mockMvc.perform(post("/player/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playerDTO)));


        // Check the HTTP status code
        response.andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Player inserted successfully"));

    }

    @Test
    public void PlayerController_AddPlayerWhenSameNumberExists_Return400() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("error", "There is a player with the same number");
        // Mocking the Service
        given(playerService.insertPlayer(ArgumentMatchers.any()))
                .willReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(responseS));

        
        ResultActions response = mockMvc.perform(post("/player/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playerDTO)));
        // Check the HTTP status code
        response.andExpect(status().isBadRequest())
                .andExpect(jsonPath("error").value("There is a player with the same number"));

    }

    @Test
    public void PlayerController_AddTeamWhenInvalidShirtNumber_Return400() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("error", "Give a number between 1 to 99");
        // Mocking the Service
        given(playerService.insertPlayer(ArgumentMatchers.any()))
                .willReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(responseS));

        
        ResultActions response = mockMvc.perform(post("/player/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playerDTO)));
        // Check the HTTP status code
        response.andExpect(status().isBadRequest())
                .andExpect(jsonPath("error").value("Give a number between 1 to 99"));

    }

    @Test
    public void PlayerController_DeletePlayerWhenPlayerExists_Return200() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("message", "Player deleted successfully");

        // Mocking the Service
        given(playerService.deletePlayer(ArgumentMatchers.any())).willReturn(ResponseEntity.ok(responseS));

        
        ResultActions response = mockMvc.perform(delete("/player/delete/{id}", validId));

        // Check the HTTP status code
        response.andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Player deleted successfully"));
    }

    @Test
    public void PlayerController_DeletePlayerWhenPlayerDoesNotExists_Return404() throws Exception {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error", "Player doesn't exist");
        // Mocking the Service
        given(playerService.deletePlayer(ArgumentMatchers.any()))
                .willReturn(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMap));

        
        ResultActions response = mockMvc.perform(delete("/player/delete/{id}", validId));

        // Check the HTTP status code
        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("error")
                        .value("Player doesn't exist"));

    }

    @Test
    public void PlayerController_DeletePlayerWhenSendsCharactersInID_Return500() throws Exception {

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error", "java.lang.NumberFormatException: For input string: "+notValidId);
        // Mocking the Service
        given(playerService.deletePlayer(ArgumentMatchers.any()))
                .willReturn(ResponseEntity.internalServerError()
                        .body(responseMap));

        
        ResultActions response = mockMvc.
                perform(delete("/player/delete/{id}", notValidId));

        // Check the HTTP status code
        response.andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("java.lang.NumberFormatException: For input string: " + notValidId));

    }

    @Test
    public void PlayerController_GetAll_returnsListOfTeams() throws Exception {

        given(playerService.getAllPlayers()).willReturn(list);

        ResultActions response = mockMvc.perform(get("/player/getAll"));

        // Check the HTTP status code
        response.andExpect(status().isOk())

                // Check the JSON structure and values
                .andExpect(jsonPath("$", hasSize(2))) // Assuming the list has two teams
                .andExpect(jsonPath("$[0].name", is("Eyad Games")))
                .andExpect(jsonPath("$[1].name", is("Abo Treika")))
                .andExpect(jsonPath("$[0].position", is(Position.MID.name())))
                .andExpect(jsonPath("$[1].position", is(Position.MID.name())))
                .andExpect(jsonPath("$[0].number_in_team", is(21)))
                .andExpect(jsonPath("$[1].number_in_team", is(22)))
        ;
    }

    @Test
    public void PlayerController_DeleteAll_returnsListOfTeams() throws Exception {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "All Players deleted successfully");

        given(playerService.deleteAllPlayers()).willReturn(ResponseEntity.ok(responseMap));

        ResultActions response = mockMvc.perform(delete("/player/deleteAll"));

        // Check the HTTP status code
        response.andExpect(status().isOk())

                // Check the JSON structure and values
                .andExpect(jsonPath("message").value("All Players deleted successfully"));
    }

    @Test
    public void PlayerController_UpdateTeam_Return200() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("message", "Player Updated successfully");

        // Mocking the Service
        given(playerService.updatePlayer(ArgumentMatchers.any())).
                willReturn(ResponseEntity.ok(responseS));

        
        ResultActions response = mockMvc.perform(post("/player/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(player)));
        // Check the HTTP status code
        response.andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Player Updated successfully"));

    }

    @Test
    public void PlayerController_UpdateTeam_Return404() throws Exception {
        Map<String, String> responseS = new HashMap<>();
        responseS.put("error", "Player doesn't exist");

        // Mocking the Service
        given(playerService.updatePlayer(ArgumentMatchers.any())).
                willReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseS));


        ResultActions response = mockMvc.perform(post("/player/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(player)));
        // Check the HTTP status code
        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("error").value("Player doesn't exist"));
    }

}

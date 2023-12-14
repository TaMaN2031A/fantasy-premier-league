package com.fantasy.fantasyleague.RealLeague.Controller;

import com.fantasy.fantasyleague.RealLeague.Controller.PlayedMatchController;
import com.fantasy.fantasyleague.RealLeague.DTO.MatchStatisticsDTO;
import com.fantasy.fantasyleague.RealLeague.Service.MatchServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PlayedMatchController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PlayedMatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchServiceImpl matchService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void ControllerTest() throws Exception {
        List<String> x= new ArrayList<>();
        x.add("mohamed arous");
        x.add("amr ahmed");
        x.add("amr ahmed1");
        x.add("amr ahmed2");
        x.add("amr ahmed3");
        x.add("amr ahmed3");
        MatchStatisticsDTO matchStatisticsDTO = MatchStatisticsDTO
                .builder()
                .homePlayersPlayed(List.copyOf(x))
                .awayPlayersPlayed(List.copyOf(x))
                .away("liverpool")
                .home("man city")
                .build();
        when(matchService.addMatchStatiscis(any(MatchStatisticsDTO.class)))
                .thenReturn("Match is added successfully");
        ResultActions result = mockMvc.perform(post("/playedMatch/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(matchStatisticsDTO)));

        result.andExpect(status().isOk());

    }

}

package com.fantasy.fantasyleague.FaqRule.Controller;

import com.fantasy.fantasyleague.FaqRule.Model.Response;
import com.fantasy.fantasyleague.FaqRule.Model.Rule;
import com.fantasy.fantasyleague.FaqRule.Service.RuleServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RuleController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class RuleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleServiceImpl ruleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testInsertRule() throws Exception {
        JsonNode ruleJson = objectMapper.readTree("{\"rule\": \"New Rule\"}");

        when(ruleService.insertRule(ArgumentMatchers.any(JsonNode.class)))
                .thenReturn(Response.INSERT_SUCCESS.getMessage());

        mockMvc.perform(MockMvcRequestBuilders.post("/rule/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ruleJson)))
                .andExpect(status().isOk())
                .andExpect(content().string(Response.INSERT_SUCCESS.getMessage()));

        verify(ruleService, times(1)).insertRule(ArgumentMatchers.any(JsonNode.class));
    }

    @Test
    public void testDeleteRule() throws Exception {
        when(ruleService.deleteRule(anyInt())).thenReturn(Response.DELETE_SUCCESS.getMessage());

        mockMvc.perform(MockMvcRequestBuilders.delete("/rule/delete/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Response.DELETE_SUCCESS.getMessage()));

        verify(ruleService, times(1)).deleteRule(1);
    }

    @Test
    public void testUpdateRule() throws Exception {
        Rule updatedRule = new Rule();
        updatedRule.setRuleID(1);
        updatedRule.setRule("Updated Rule");

        when(ruleService.updateRule(any(Rule.class))).thenReturn(Response.UPDATE_SUCCESS.getMessage());

        mockMvc.perform(MockMvcRequestBuilders.put("/rule/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRule)))
                .andExpect(status().isOk())
                .andExpect(content().string(Response.UPDATE_SUCCESS.getMessage()));

        verify(ruleService, times(1)).updateRule(any(Rule.class));
    }

    @Test
    public void testGetAll() throws Exception {
        Rule rule1 = new Rule();
        rule1.setRuleID(1);
        rule1.setRule("Rule 1");

        Rule rule2 = new Rule();
        rule2.setRuleID(2);
        rule2.setRule("Rule 2");

        List<Rule> allRules = Arrays.asList(rule1, rule2);

        when(ruleService.getAllRule()).thenReturn(allRules);

        mockMvc.perform(MockMvcRequestBuilders.get("/rule/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(allRules)));

        verify(ruleService, times(1)).getAllRule();
    }

    @Test
    public void testDeleteAll() throws Exception {
        when(ruleService.deleteAllRule()).thenReturn(Response.DELETE_SUCCESS.getMessage());

        mockMvc.perform(MockMvcRequestBuilders.delete("/rule/deleteAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Response.DELETE_SUCCESS.getMessage()));

        verify(ruleService, times(1)).deleteAllRule();
    }

}

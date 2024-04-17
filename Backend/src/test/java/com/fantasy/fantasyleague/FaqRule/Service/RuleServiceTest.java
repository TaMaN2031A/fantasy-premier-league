package com.fantasy.fantasyleague.FaqRule.Service;
import com.fantasy.fantasyleague.FaqRule.Model.Response;
import com.fantasy.fantasyleague.FaqRule.Model.Rule;
import com.fantasy.fantasyleague.FaqRule.Repository.RuleRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RuleServiceTest {

    @Mock
    private RuleRepository ruleRepository;

    @InjectMocks
    private RuleServiceImpl ruleService;

    @Test
    public void RuleInsert() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("rule", "Sample Rule");

        Rule expectedRule = new Rule();
        expectedRule.setRule("Sample Rule");

        when(ruleRepository.save(Mockito.any(Rule.class))).thenReturn(expectedRule);

        String result = ruleService.insertRule(jsonNode);
        System.out.println(result);

        assertEquals(Response.INSERT_SUCCESS.getMessage(), result);
    }


    @Test
    public void testUpdateRule_Success() {
        Rule existingRule = new Rule();
        existingRule.setRuleID(1);
        existingRule.setRule("Existing Rule");

        when(ruleRepository.existsById(1)).thenReturn(true);
        when(ruleRepository.getReferenceById(1)).thenReturn(existingRule);

        Rule updatedRule = new Rule();
        updatedRule.setRuleID(1);
        updatedRule.setRule("Updated Rule");

        String result = ruleService.updateRule(updatedRule);

        verify(ruleRepository, times(1)).existsById(1);
        verify(ruleRepository, times(1)).getReferenceById(1);
        verify(ruleRepository, times(1)).save(existingRule);

        assertEquals(Response.UPDATE_SUCCESS.getMessage(), result);
        assertEquals("Updated Rule", existingRule.getRule());
    }

    @Test
    public void testUpdateRule_Failure() {
        Rule nonExistingRule = new Rule();
        nonExistingRule.setRuleID(2);
        nonExistingRule.setRule("Non-existing Rule");

        when(ruleRepository.existsById(2)).thenReturn(false);

        String result = ruleService.updateRule(nonExistingRule);

        verify(ruleRepository, times(1)).existsById(2);
        verify(ruleRepository, never()).getReferenceById(anyInt());
        verify(ruleRepository, never()).save(any());

        assertEquals(Response.UPDATE_FAIL.getMessage(), result);
    }

    @Test
    public void testDeleteRule_Success() {
        int existingRuleId = 1;

        when(ruleRepository.existsById(existingRuleId)).thenReturn(true);
        doNothing().when(ruleRepository).deleteById(existingRuleId);

        String result = ruleService.deleteRule(existingRuleId);

        verify(ruleRepository, times(1)).existsById(existingRuleId);
        verify(ruleRepository, times(1)).deleteById(existingRuleId);

        assertEquals(Response.DELETE_SUCCESS.getMessage(), result);
    }

    @Test
    public void testDeleteRule_Failure() {
        int nonExistingRuleId = 2;

        when(ruleRepository.existsById(nonExistingRuleId)).thenReturn(false);

        String result = ruleService.deleteRule(nonExistingRuleId);

        verify(ruleRepository, times(1)).existsById(nonExistingRuleId);
        verify(ruleRepository, never()).deleteById(anyInt());

        assertEquals(Response.DELETE_FAIL.getMessage(), result);
    }

    @Test
    public void testGetAllRule() {
        List<Rule> expectedRules = new ArrayList<>();
        Rule rule1 = new Rule();
        rule1.setRuleID(1);
        rule1.setRule("Existing Rule");
        Rule rule2 = new Rule();
        rule2.setRuleID(1);
        rule2.setRule("Existing Rule");
        expectedRules.add(rule1);
        expectedRules.add(rule2);

        when(ruleRepository.findAll()).thenReturn(expectedRules);

        List<Rule> result = ruleService.getAllRule();

        assertEquals(expectedRules.size(), result.size());
        assertEquals(expectedRules, result);
    }

    @Test
    public void testDeleteAllRule() {
        doNothing().when(ruleRepository).deleteAll();

        String result = ruleService.deleteAllRule();

        verify(ruleRepository, times(1)).deleteAll();
        assertEquals(Response.DELETE_SUCCESS.getMessage(), result);
    }

}

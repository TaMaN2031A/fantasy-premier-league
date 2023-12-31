package com.fantasy.fantasyleague.FaqRule.Service;

import com.fantasy.fantasyleague.FaqRule.Model.Response;
import com.fantasy.fantasyleague.FaqRule.Model.Rule;
import com.fantasy.fantasyleague.FaqRule.Repository.RuleRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService{

    @Autowired
    RuleRepository ruleRepository;

    @Override
    public String insertRule(JsonNode jsonRule) {
        String rule = jsonRule.get("rule").asText();
        Rule newRule = new Rule();
        newRule.setRule(rule);
        ruleRepository.save(newRule);
        return Response.INSERT_SUCCESS.getMessage();
    }

    @Override
    public String updateRule(Rule rule) {
        if(!ruleRepository.existsById(rule.getRuleID()))
            return Response.UPDATE_FAIL.getMessage();
        Rule ruleToUpdate = ruleRepository.getReferenceById(rule.getRuleID());
        ruleToUpdate.setRule(rule.getRule());
        ruleRepository.save(ruleToUpdate);
        return Response.UPDATE_SUCCESS.getMessage();
    }

    @Override
    public String deleteRule(int rule) {
        if(!ruleRepository.existsById(rule))
            return Response.DELETE_FAIL.getMessage();
        ruleRepository.deleteById(rule);
        return Response.DELETE_SUCCESS.getMessage();
    }

    @Override
    public List<Rule> getAllRule(){
        return ruleRepository.findAll();
    }

    @Override
    public String deleteAllRule(){
        ruleRepository.deleteAll();
        return Response.DELETE_SUCCESS.getMessage();
    }
}

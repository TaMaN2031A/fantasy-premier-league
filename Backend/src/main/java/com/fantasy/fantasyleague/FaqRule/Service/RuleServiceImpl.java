package com.fantasy.fantasyleague.FaqRule.Service;

import com.fantasy.fantasyleague.FaqRule.Model.Rule;
import com.fantasy.fantasyleague.FaqRule.Repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RuleServiceImpl implements RuleService{

    private String deleteResponse = "Deleted Successfully";
    private String insertResponse = "Inserted Successfully";
    private String updateResponse = "Updated Successfully";
    private String deleteResponseF = "Unsuccessful Delete";
    private String updateResponseF = "Unsuccessful Update";

    @Autowired
    RuleRepository ruleRepository;
    @Override
    public String insertRule(Rule rule) {
        ruleRepository.save(rule);
        return insertResponse;
    }

    @Override
    public String updateRule(Rule rule) {
        if(!ruleRepository.existsById(rule.getRuleID()))
            return updateResponseF;
        Rule ruleToUpdate = ruleRepository.getReferenceById(rule.getRuleID());
        ruleToUpdate.setRule(rule.getRule());
        ruleToUpdate.setDate(rule.getDate());
        ruleRepository.save(ruleToUpdate);
        return updateResponse;
    }

    @Override
    public String deleteRule(Rule rule) {
        if(!ruleRepository.existsById(rule.getRuleID()))
            return deleteResponseF;
        ruleRepository.deleteById(rule.getRuleID());
        return deleteResponse;
    }

    @Override
    public List<Rule> getAllRule(){
        return ruleRepository.findAll();
    }

    @Override
    public String deleteAllRule(){
        ruleRepository.deleteAll();
        return deleteResponse;
    }
}

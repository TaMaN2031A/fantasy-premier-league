package com.fantasy.fantasyleague.FaqRule.Service;

import com.fantasy.fantasyleague.FaqRule.Model.FAQ;
import com.fantasy.fantasyleague.FaqRule.Model.Rule;
import com.fantasy.fantasyleague.FaqRule.Repository.FAQRepository;
import com.fantasy.fantasyleague.FaqRule.Repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RuleServiceImpl implements RuleService{

    String deleteResponse = "Deleted Successfully";
    String insertResponse = "Inserted Successfully";
    String updateResponse = "Updated Successfully";

    @Autowired
    RuleRepository ruleRepository;
    @Override
    public String insertRule(Rule rule) {
        ruleRepository.save(rule);
        return insertResponse;
    }

    @Override
    public String updateRule(Rule rule) {
        ruleRepository.deleteById(rule.getRuleID());
        ruleRepository.save(rule);
        return updateResponse;
    }

    @Override
    public String deleteRule(Rule rule) {
        ruleRepository.deleteById(rule.getRuleID());
        return deleteResponse;
    }

    @Override
    public List<Rule> getAllRule(){
        return ruleRepository.findAll();
    }
}

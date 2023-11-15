package com.fantasy.fantasyleague.FaqRule.Service;

import com.fantasy.fantasyleague.FaqRule.Model.FAQ;
import com.fantasy.fantasyleague.FaqRule.Model.Rule;

import java.util.List;

public interface RuleService {
    String insertRule(Rule rule);
    String updateRule(Rule rule);
    String deleteRule(Rule rule);
    public List<Rule> getAllRule();
}

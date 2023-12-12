package com.fantasy.fantasyleague.Registiration.FaqRule.Service;

import com.fantasy.fantasyleague.Registiration.FaqRule.Model.Rule;

import java.util.List;

public interface RuleService {
    String insertRule(Rule rule);
    String updateRule(Rule rule);
    String deleteRule(Rule rule);
    List<Rule> getAllRule();
    String deleteAllRule();
}

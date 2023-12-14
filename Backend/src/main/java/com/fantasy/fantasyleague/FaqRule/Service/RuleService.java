package com.fantasy.fantasyleague.FaqRule.Service;

import com.fantasy.fantasyleague.FaqRule.Model.Rule;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface RuleService {
    String insertRule(JsonNode rule);
    String updateRule(Rule rule);
    String deleteRule(int id);
    List<Rule> getAllRule();
    String deleteAllRule();
}

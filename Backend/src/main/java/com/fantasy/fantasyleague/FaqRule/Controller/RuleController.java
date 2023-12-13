package com.fantasy.fantasyleague.FaqRule.Controller;

import com.fantasy.fantasyleague.FaqRule.Model.Rule;
import com.fantasy.fantasyleague.FaqRule.Service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/rule")
public class RuleController {

    @Autowired
    RuleService ruleService;
    @PostMapping("/insert")
    public String insertRule(@RequestBody Rule rule) {
        return ruleService.insertRule(rule);
    }
    @DeleteMapping("/delete")
    public String deleteRule(@RequestBody Rule rule) {
        return ruleService.deleteRule(rule);
    }
    @PostMapping("/update")
    public String updateRule(@RequestBody Rule rule) {
        return ruleService.updateRule(rule);
    }
    @GetMapping("/getAll")
    public List<Rule> getAllList() {
        return ruleService.getAllRule();
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllList() {
        return ruleService.deleteAllRule();
    }

}

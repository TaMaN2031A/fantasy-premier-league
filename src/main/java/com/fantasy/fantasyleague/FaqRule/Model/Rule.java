package com.fantasy.fantasyleague.FaqRule.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Rule {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int ruleID;
    private String rule;
    private Date date;

    public Rule(String rule, Date date) {
        this.rule = rule;
        this.date = date;
    }

    public int getRuleID() {
        return ruleID;
    }

    public void setRuleID(int ruleID) {
        this.ruleID = ruleID;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

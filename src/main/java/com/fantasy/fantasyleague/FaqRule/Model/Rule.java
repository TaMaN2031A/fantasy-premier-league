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
    @Getter
    @Setter
    private int ruleID;
    @Getter
    @Setter
    private String rule;
    @Getter
    @Setter
    private Date date;

    public Rule(String rule, Date date) {
        this.rule = rule;
        this.date = date;
    }

}

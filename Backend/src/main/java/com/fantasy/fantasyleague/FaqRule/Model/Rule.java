package com.fantasy.fantasyleague.FaqRule.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@Entity
public class Rule {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int ruleID;
    private String rule;

}

package com.fantasy.fantasyleague.FaqRule.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
public class FAQ {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int faqID;
    private String question;
    private String answer;
    private Date date;

}

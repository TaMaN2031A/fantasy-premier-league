package com.fantasy.fantasyleague.FaqRule.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FAQ {
    @Setter
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int faqID;
    @Column(nullable=false, length=512)
    private String question;
    @Column(nullable=false, length=512)
    private String answer;
    private Date date;

    public FAQ(String question, String answer, Date date) {
        super();
        this.question = question;
        this.answer = answer;
        this.date = date;
    }

}

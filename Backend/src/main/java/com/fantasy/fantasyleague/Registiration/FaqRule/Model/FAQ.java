package com.fantasy.fantasyleague.Registiration.FaqRule.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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

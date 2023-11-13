package com.fantasy.fantasyleague.FaqRule;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class FAQ {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long faqID;
    @Column(name="question", nullable=false, length=512)
    private String question;
    @Column(name="answer", nullable=false, length=512)
    private String answer;
    private Date date;

    public FAQ(String question, String answer, Date date) {
        super();
        this.question = question;
        this.answer = answer;
        this.date = date;
    }

    public long getFaqID() {
        return faqID;
    }

    public void setFaqID(long faqID) {
        this.faqID = faqID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

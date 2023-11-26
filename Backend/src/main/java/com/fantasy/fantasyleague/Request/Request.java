package com.fantasy.fantasyleague.Request;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@IdClass(RequestKey.class)
public class Request {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int requestID;
    @Id
    private String userName;
    @Id
    private String email;

    private Date date;
    private String password;
    private String region;

    public Request() {
        // Default constructor
    }
    public Request(String userName, String email, Date date, String password, String region) {
        this.userName = userName;
        this.email = email;
        this.date = date;
        this.password = password;
        this.region = region;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}

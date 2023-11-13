package com.fantasy.fantasyleague.Player;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int ID;
    private String name;
    private int red_cards;
    private int yellow_cards;
    private int goals;
    private int assists;
    private int saved;
    private char position;
    private int number_in_team;
    private int team_id;
    private String photo_link;
    private double price;
    public Player(String name, int red_cards, int yellow_cards, int goals, int assists, int saved, char position, int number_in_team, int team_id, String photo_link, double price) {
        this.name = name;
        this.red_cards = red_cards;
        this.yellow_cards = yellow_cards;
        this.goals = goals;
        this.assists = assists;
        this.saved = saved;
        this.position = position;
        this.number_in_team = number_in_team;
        this.team_id = team_id;
        this.photo_link = photo_link;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRed_cards() {
        return red_cards;
    }

    public void setRed_cards(int red_cards) {
        this.red_cards = red_cards;
    }

    public int getYellow_cards() {
        return yellow_cards;
    }

    public void setYellow_cards(int yellow_cards) {
        this.yellow_cards = yellow_cards;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getSaved() {
        return saved;
    }

    public void setSaved(int saved) {
        this.saved = saved;
    }

    public char getPosition() {
        return position;
    }

    public void setPosition(char position) {
        this.position = position;
    }

    public int getNumber_in_team() {
        return number_in_team;
    }

    public void setNumber_in_team(int number_in_team) {
        this.number_in_team = number_in_team;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getPhoto_link() {
        return photo_link;
    }

    public void setPhoto_link(String photo_link) {
        this.photo_link = photo_link;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

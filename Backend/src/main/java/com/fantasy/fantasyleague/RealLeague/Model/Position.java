package com.fantasy.fantasyleague.RealLeague.Model;

public enum Position {
    GK("GOAL KEEPER"),
    DEF("DEFENDER"),
    MID("MIDFIELDER"),
    FWD("FORWARD");

    private final String position;

    Position(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}

package com.fantasy.fantasyleague.RealLeague.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopPlayer {
    private String name;
    private int value;

    public TopPlayer(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public TopPlayer() {
    }

}

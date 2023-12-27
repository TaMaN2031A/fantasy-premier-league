package com.fantasy.fantasyleague.fantasyGame.Model;

import lombok.Getter;

@Getter
public enum Lock {
    X("X");

    public final String value;

    Lock(String value) {
        this.value = value;
    }

}

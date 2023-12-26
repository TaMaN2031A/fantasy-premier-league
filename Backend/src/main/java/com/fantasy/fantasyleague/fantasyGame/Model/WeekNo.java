package com.fantasy.fantasyleague.fantasyGame.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "week_no")
public class WeekNo {

    public enum Lock {
        X
    }

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "`Lock`", nullable = false, length = 1)
    private Lock lock = Lock.X;

    @Column(nullable = false)
    private int weekNo = 0;

    // Constructors, getters, setters, and other methods


}

package com.fantasy.fantasyleague.fantasyGame.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "week_no")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WeekNo {

    public enum Lock {
        X("X");

        Lock(String x) {
        }
    }

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "`Lock`", nullable = false, length = 1)
    private Lock lock = Lock.X;

    @Column(nullable = false)
    private int weekNo = 0;

    // Constructors, getters, setters, and other methods
}

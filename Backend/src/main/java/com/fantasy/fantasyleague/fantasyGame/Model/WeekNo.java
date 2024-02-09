package com.fantasy.fantasyleague.fantasyGame.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "week_no")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeekNo {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "`Lock`", nullable = false)
    private Lock lock = Lock.X;

    @Column(nullable = false)
    private int weekNo = 0;

    // Constructors, getters, setters, and other methods
}
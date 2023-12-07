package com.fantasy.fantasyleague.RealLeague.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UpcomingMatch {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int ID;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "home_id", referencedColumnName = "id")
    private Team home;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "away_id", referencedColumnName = "id")
    private Team away;
    private int week;
    private String stadium;

    public UpcomingMatch(Team home, Team away, int week, String stadium) {
        this.home = home;
        this.away = away;
        this.week = week;
        this.stadium = stadium;
    }
}

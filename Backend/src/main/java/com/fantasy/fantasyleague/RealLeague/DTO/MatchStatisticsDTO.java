package com.fantasy.fantasyleague.RealLeague.DTO;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchStatisticsDTO {
    private String home;
    private String away;
    private String manOfMatch;


    @Builder.Default
    private List<String> awayPlayersYellowCards = new ArrayList<>();
    @Builder.Default
    private List<String> homePlayersYellowCards= new ArrayList<>();
    @Builder.Default
    private List<String> awayPlayersRedCards= new ArrayList<>();
    @Builder.Default
    private List<String> homePlayersRedCards= new ArrayList<>();
    @Builder.Default
    private List<String> awayPlayersScore= new ArrayList<>();
    @Builder.Default
    private List<String> homePlayersScore= new ArrayList<>();
    @Builder.Default
    private List<String> awayPlayersAssist= new ArrayList<>();
    @Builder.Default
    private List<String> homePlayersAssist= new ArrayList<>();
    @Builder.Default
    private List<String> awayPlayersSaves= new ArrayList<>();
    @Builder.Default
    private List<String> homePlayersSaves= new ArrayList<>();
    @Builder.Default
    private List<String> awayPlayersPlayed= new ArrayList<>();
    @Builder.Default
    private List<String> homePlayersPlayed= new ArrayList<>();

}

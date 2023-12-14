package com.fantasy.fantasyleague.RealLeague.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UpcomingMatchInsertionDTO {
    private int week;
    private int homeID;
    private int awayID;
    private String stadium;

}

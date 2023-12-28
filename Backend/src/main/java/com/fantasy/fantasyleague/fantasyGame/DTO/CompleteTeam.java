package com.fantasy.fantasyleague.fantasyGame.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompleteTeam {
    private String username;
    private List<String> players;
}

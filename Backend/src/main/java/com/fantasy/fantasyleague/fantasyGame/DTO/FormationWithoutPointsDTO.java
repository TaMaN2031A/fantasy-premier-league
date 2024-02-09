package com.fantasy.fantasyleague.fantasyGame.DTO;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FormationWithoutPointsDTO {
    private List<PlayerWithoutPoints> players;
    private Player Captain;
    private Player ViceCaptain;
    private boolean BenchBoost;
    private boolean TripleCaptain;
}

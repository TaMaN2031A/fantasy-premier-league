package com.fantasy.fantasyleague.FantasyGame.Model.PlayerInTeam;

import com.fantasy.fantasyleague.RealLeague.Model.Player;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.fantasyGame.Model.PlayerInTeam.FormationHistory;
import org.junit.jupiter.api.Test;

class FormationHistoryDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link FormationHistory#FormationHistory()}
     *   <li>{@link FormationHistory#setPlayer(Player)}
     *   <li>{@link FormationHistory#setStarter(boolean)}
     *   <li>{@link FormationHistory#setUser(User)}
     *   <li>{@link FormationHistory#setWeekNum(int)}
     *   <li>{@link FormationHistory#getPlayer()}
     *   <li>{@link FormationHistory#getUser()}
     *   <li>{@link FormationHistory#getWeekNum()}
     *   <li>{@link FormationHistory#isStarter()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        // TODO: Populate arranged inputs
        FormationHistory actualFormationHistory = new FormationHistory();
        actualFormationHistory.setPlayer(null);
        boolean isStarter = false;
        actualFormationHistory.setStarter(isStarter);
        User user = null;
        actualFormationHistory.setUser(null);
        int weekNum = 0;
        actualFormationHistory.setWeekNum(weekNum);
        Player actualPlayer = actualFormationHistory.getPlayer();
        User actualUser = actualFormationHistory.getUser();
        int actualWeekNum = actualFormationHistory.getWeekNum();
        boolean actualIsStarterResult = actualFormationHistory.isStarter();

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link FormationHistory#FormationHistory(Player, User, int, boolean)}
     *   <li>{@link FormationHistory#setPlayer(Player)}
     *   <li>{@link FormationHistory#setStarter(boolean)}
     *   <li>{@link FormationHistory#setUser(User)}
     *   <li>{@link FormationHistory#setWeekNum(int)}
     *   <li>{@link FormationHistory#getPlayer()}
     *   <li>{@link FormationHistory#getUser()}
     *   <li>{@link FormationHistory#getWeekNum()}
     *   <li>{@link FormationHistory#isStarter()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        // TODO: Populate arranged inputs
        Player player = null;
        FormationHistory actualFormationHistory = getFormationHistory(null);
        Player actualPlayer = actualFormationHistory.getPlayer();
        User actualUser = actualFormationHistory.getUser();
        int actualWeekNum = actualFormationHistory.getWeekNum();
        boolean actualIsStarterResult = actualFormationHistory.isStarter();

        // Assert
        // TODO: Add assertions on result
    }

    private static FormationHistory getFormationHistory(Player player) {
        User user = null;
        int weekNum = 0;
        boolean isStarter = false;

        // Act
        FormationHistory actualFormationHistory = new FormationHistory(player, null, weekNum, isStarter);
        Player player2 = null;
        actualFormationHistory.setPlayer(null);
        boolean isStarter2 = false;
        actualFormationHistory.setStarter(isStarter2);
        User user2 = null;
        actualFormationHistory.setUser(null);
        int weekNum2 = 0;
        actualFormationHistory.setWeekNum(weekNum2);
        return actualFormationHistory;
    }
}

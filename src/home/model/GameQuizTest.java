package home.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import home.model.building.BuildingType;

/**
 * some test on game based on quiz.
 */
public class GameQuizTest {
    /**
     * test basic thing in the game based on quiz.
     */
    @Test
    public void testBasic() {
        Game.getGame().newGame();
        try {
            Game.getGame().endCurrentQuiz();
            fail();
        } catch (IllegalStateException exc) {
            assertNotNull(exc);
        }
        assertFalse(Game.getGame().getCurrentQuiz().isPresent());
        Game.getGame().createQuiz(BuildingType.SCHOOL);
        assertTrue(Game.getGame().getCurrentQuiz().isPresent());
        try {
            Game.getGame().createQuiz(BuildingType.SPORT_CENTER);
            fail();
        } catch (IllegalStateException exc) {
            assertNotNull(exc);
        }
    }
}

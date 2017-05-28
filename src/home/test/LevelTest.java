package home.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import home.model.level.Level;
/*Some test on status*/
/**
 */
public class LevelTest {
    private static final int EXPERIENCE = 1000;
    /**
     * 
     */
    @Test
    public void testLevelAge() {
        final Level.Building level = Level.Building.createBuildingLevel();
        final Level.Age age = Level.Age.createAgeLevel();
        assertEquals(age.getExperienceAmount(), EXPERIENCE);
        assertFalse(age.nextAge(1).isPresent());
        try {
            age.nextAge(-1);
            fail();
        } catch (IllegalArgumentException exc) {
            assertNotNull(exc);
        }
        assertTrue(age.nextAge(EXPERIENCE + 1).isPresent());
        //check if i can create a new level with a difference maximum successive level
        level.maxiumLevelchanged(2);
        try {
            level.maxiumLevelchanged(1);
            fail();
        } catch (IllegalArgumentException exv) {
            assertNotNull(exv);
        }
        assertFalse(level.nextLevel(1).isPresent());
        checkNegativeExperience(level);
        assertTrue(level.nextLevel(level.getExperienceAmount() + 1).isPresent());
        final Level.Building blockedLevel = level.nextLevel(level.getExperienceAmount() + 1).get();
        try {
            //it can go on next level because it is blocked now
            blockedLevel.nextLevel(1);
            fail();
        } catch (IllegalStateException exc) {
            assertNotNull(exc);
        }
    }
    private void checkNegativeExperience(final Level.Building level) {
        try {
            level.nextLevel(-1);
            fail();
        } catch (IllegalArgumentException exc) {
            assertNotNull(exc);
        }
    }
}

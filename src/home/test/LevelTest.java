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
        final Level age = Level.Age.createAgeLevel();
        assertEquals(age.getExperienceAmount(), EXPERIENCE);
        assertFalse(age.nextLevel(1));
        checkNegativeExperience(age);
        assertTrue(age.nextLevel(EXPERIENCE + 1));
        level.setMaxiumLevel(2);
        assertFalse(level.nextLevel(1));
        checkNegativeExperience(level);
        assertTrue(level.nextLevel(level.getExperienceAmount() + 1));
        try {
            level.nextLevel(1);
            fail();
        } catch (IllegalStateException exc) {
            assertNotNull(exc);
        }
    }
    private void checkNegativeExperience(final Level level) {
        try {
            level.nextLevel(-1);
            fail();
        } catch (IllegalArgumentException exc) {
            assertNotNull(exc);
        }
    }
}

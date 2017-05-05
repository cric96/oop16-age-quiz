package home.model.level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
/*Some test on status*/
/**
 */
public class LevelTest {
    /**
     * 
     */
    @Test
    public void testLevelAge() {
        final Level.Building level = Level.Building.createBuildingLevel();
        final Level age = Level.Age.createAgeLevel();
        assertEquals(age.getExperienceAmount(), AgeEnum.ETA_DELLA_PIETRA.getExperience());
        assertFalse(age.nextLevel(1));
        assertTrue(age.nextLevel(AgeEnum.ETA_DELLA_PIETRA.getExperience() + 1));
        level.setMaxiumLevel(2);
        assertFalse(level.nextLevel(1));
        assertTrue(level.nextLevel(level.getExperienceAmount() + 1));
        try {
            level.nextLevel(1);
            fail();
        } catch (IllegalStateException exc) {
            assertNotNull(exc);
        }
    }
}

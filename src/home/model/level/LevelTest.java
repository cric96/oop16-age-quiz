package home.model.level;

import static org.junit.Assert.*;

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
        final Level level = Level.Building.createBuildingLevel();
        System.out.println(level.toString());
        final Level age = Level.Age.createAgeLevel();
        System.out.println(age.toString());
        for (int i = 0; i < AgeEnum.values().length - 1; i++) {
            System.out.println(age.getLevelInfo());
            age.nextLevel();
        }
        System.out.println(age.getLevelInfo());
        try {
            age.nextLevel();
            fail();
        } catch (IllegalStateException exc) { }
    }
}

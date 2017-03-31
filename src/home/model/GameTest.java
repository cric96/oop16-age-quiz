package home.model;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Set;

import org.junit.Test;

import home.model.building.ImmutableAgeBuilding;
import home.model.status.StatusName;
import home.model.utility.Pair;

/**
 * some test in the game.
 */
public class GameTest {
    private static final int EXPERIENCE = 1000;
    private static final int MAX_STATUS = 100;
    private static final String FILE_NAME = "C:\\Users\\Gianluca\\prova.obj";
    /**
     * simple test for the interface Game.
     */
    /**
     * test to check save.
     */
    @Test
    public void testSave() {
        final File fileName = new File(FILE_NAME);
        Game.getGame().newGame();
        Game.getGame().getCurrentKingdom().addExperience(EXPERIENCE);
        Game.getGame().save(fileName);
        Game.getGame().getCurrentKingdom().addExperience(EXPERIENCE);
        Game.getGame().load(fileName);
        assertEquals(Game.getGame().getCurrentKingdom().getExperienceAmount(), EXPERIENCE);
        fileName.delete();
    }
    /**
     * simple test for the kingdom.
     */
    @Test
    public void testKingdom() {
        Game.getGame().newGame();
        final Kingdom king = Game.getGame().getCurrentKingdom();
        System.out.println(king.getAgeName());
        //at the beginning the experience amount is equals to 0
        assertEquals(king.getExperienceAmount(), 0);
        //i can't go on the next level without experience
        assertFalse(king.nextAge());
        try {
            king.decExperiene(-EXPERIENCE);
            fail("can't use negative value");
        } catch (IllegalArgumentException exc) { }
        try {
            king.decExperiene(EXPERIENCE);
            fail("no more experience!");
        } catch (IllegalArgumentException exc) { }
        king.addExperience(EXPERIENCE);
        //now i can go on the next age
        assertTrue(king.nextAge());
        System.out.println(king.getAgeName());
        assertSame(king.getExperienceAmount(), 0);
        //if i go in the next age the incremental value must be change
        assertNotSame(king.getAge().getIncrementalLevel(), 0);
        assertSame(king.getAge().getIncrementalLevel(), 1);
        //at the beginning all stats are equal to zero
        king.getStatusStatistic().forEach((x, y) -> assertSame(y, 0));
        king.changeStatus(StatusName.HAPPINESS, MAX_STATUS);
        king.getStatusStatistic().forEach((x, y) -> {
            if (x == StatusName.HAPPINESS) {
                assertSame(y, 100);
            }
        });
        king.changeStatus(StatusName.HAPPINESS, -MAX_STATUS);
        king.getStatusStatistic().forEach((x, y) -> assertSame(y, 0));
        king.addExperience(EXPERIENCE * EXPERIENCE);
        //try if there are some bugs in age
        while (king.getAge().isUpgradable()) {
            assertTrue(king.nextAge());
        }
        try{
            king.nextAge();
            fail();
        } catch (IllegalStateException exc) { }
    }
    @Test
    public void testKingdomComponent() {
        //FINISCI QUA CHE HAI QUASI FATTO CRETINO!
        Game.getGame().newGame();
        Game.getGame().getCurrentKingdom().addExperience(EXPERIENCE);
        Game.getGame().getCurrentKingdom().nextAge();
        Set<Pair<ImmutableAgeBuilding, Boolean>> building = Game.getGame()
                                                               .getCurrentKingdom()
                                                               .getComponents(ImmutableAgeBuilding.class);
        building.forEach(System.out::println);
    }
    
}

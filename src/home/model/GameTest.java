package home.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
    private static final String BUILDING_TEST = "BUILDING_SITE";
    private static final File FILE_NAME = new File("C:\\Users\\Gianluca\\prova.obj");
    /**
     * simple test for the interface Game.
     */
    /**
     * test to check save.
     */
    @Test
    public void testSave() {
        Game.getGame().newGame();
        Game.getGame().getCurrentKingdom().addExperience(EXPERIENCE);
        Game.getGame().getCurrentKingdom().nextAge();
        Game.getGame().getCurrentKingdom().addExperience(EXPERIENCE);
        this.getBuildings(Game.getGame().getCurrentKingdom()).forEach(x -> x.getX().levelUp());
        Game.getGame().getCurrentKingdom().changeStatus(StatusName.HEALTH, MAX_STATUS);
        //check if the state of save object is legal or not
        Game.getGame().save(FILE_NAME);
        Game.getGame().getCurrentKingdom().addExperience(EXPERIENCE);
        Game.getGame().load(FILE_NAME);
        assertEquals(Game.getGame().getCurrentKingdom().getExperienceAmount(), 0);
        try {
            final Set<Pair<ImmutableAgeBuilding, Boolean>> buildings = this.getBuildings(Game.getGame().getCurrentKingdom());
            final ImmutableAgeBuilding building = this.getBuildingWithName(buildings, BUILDING_TEST);
            assertSame(building.getLevel().getIncrementalLevel(), 1);
            assertSame(Game.getGame().getCurrentKingdom().getStatusStatistic().get(StatusName.HEALTH), MAX_STATUS);
        } catch (Exception exc) {
            System.out.println(exc);
            fail();
        }
        //FILE_NAME.delete();
    }
    /**
     * simple test for the kingdom.
     */
    @Test
    public void testKingdom() {
        Game.getGame().newGame();
        final Kingdom king = Game.getGame().getCurrentKingdom();
        //at the beginning the experience amount is equals to 0
        assertEquals(king.getExperienceAmount(), 0);
        //i can't go on the next level without experience
        assertFalse(king.nextAge());
        try {
            king.decExperiene(-EXPERIENCE);
            fail("can't use negative value");
        } catch (IllegalArgumentException exc) { 
            assertNotNull(exc);
        }
        try {
            king.decExperiene(EXPERIENCE);
            fail("no more experience!");
        } catch (IllegalArgumentException exc) { 
            assertNotNull(exc);
        }
        king.addExperience(EXPERIENCE);
        //now i can go on the next age
        assertTrue(king.nextAge());
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
        try {
            king.nextAge();
            fail();
        } catch (IllegalStateException exc) { 
            assertNotNull(exc);
        }
    }
    /**
     * Test about the building associated with a kingdom.
     */
    @Test
    public void testKingdomComponent() {
        //FINISCI QUA CHE HAI QUASI FATTO CRETINO!
        Game.getGame().newGame();
        final Kingdom king = Game.getGame().getCurrentKingdom();
        king.addExperience(EXPERIENCE);
        final Set<Pair<ImmutableAgeBuilding, Boolean>> building = this.getBuildings(king);
        final ImmutableAgeBuilding site = this.getBuildingWithName(building, BUILDING_TEST);
        try {
            //try to level up a reign 
            assertTrue(site.levelUp());
            assertFalse(site.getLevel().isUpgradable());
            assertSame(king.getExperienceAmount(), 0);
        } catch (Exception exc) {
            fail("there is a building!");
        }
        //some building is blocked at the begging of game
        final int blockedBuilding = countBuilding(building);
        assertNotSame(blockedBuilding, 0);
        king.addExperience(EXPERIENCE);
        king.nextAge();
        //now some building is not blocked
        assertNotSame(blockedBuilding, countBuilding(this.getBuildings(king)));
        assertTrue(site.getLevel().isUpgradable());
    }
    private int countBuilding(final Set<Pair<ImmutableAgeBuilding, Boolean>> building) {
        return (int) building.stream().filter(x -> !x.getY())
                .count();
    }
    private Set<Pair<ImmutableAgeBuilding, Boolean>> getBuildings(final Kingdom king) {
        return king.getComponents(ImmutableAgeBuilding.class);
    }
    private ImmutableAgeBuilding getBuildingWithName(final Set<Pair<ImmutableAgeBuilding, Boolean>> building, 
                                                        final String name) {
        return building.stream().filter(x -> x.getX().getName().equals(name))
        .map(x -> x.getX())
        .findFirst().get();
    }
}

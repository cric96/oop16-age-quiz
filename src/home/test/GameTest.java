package home.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.junit.Test;

import home.model.Game;
import home.model.building.Building;
import home.model.building.BuildingType;
import home.model.image.ImageInfo;
import home.model.kingdom.AgeUpKingdomStrategy;
import home.model.kingdom.Kingdom;
import home.model.status.StatusName;
import home.utility.LocalFolder;
import home.utility.Pair;

/**
 * some test in the game.
 */

public class GameTest {
    private static final int EXPERIENCE = 1000;
    private static final int MAX_STATUS = 100;
    private static final BuildingType BUILDING_TEST = BuildingType.BUILDING_SITE;
    private static final BuildingType BUILDING_NOT_ENABLE = BuildingType.ACADEMY;
    private static final File FILE_NAME = new File(LocalFolder.LOCAL + "\\prova.obj");
    /**
     * simple test for the interface Game.
     */
    @Test
    public void testBasic() {
        //used to initialize 
        InitializeLanguage.initialize();
        Game.getGame().newGame(AgeUpKingdomStrategy.Type.SIMPLE);
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
    /**
     * test to check save.
     */
    @Test
    public void testSave() {
        Game.getGame().newGame(AgeUpKingdomStrategy.Type.SIMPLE);
        final Kingdom kingBeforeSave = Game.getGame().getCurrentKingdom();
        kingBeforeSave.addExperience(EXPERIENCE);
        //level up the building using to test this class
        kingBeforeSave.getComponents(Building.class).stream()
                                                             .map(x -> x.getX())
                                                             .filter(x -> x.getName() == BUILDING_TEST)
                                                             .forEach(x -> x.levelUp());
        kingBeforeSave.changeStatus(StatusName.HEALTH, MAX_STATUS);
        //check if the state of save object is legal or not
        try {
            Game.getGame().save(FILE_NAME);
        } catch (IOException e) {
            fail();
        }
        kingBeforeSave.addExperience(EXPERIENCE);
        try {
            Game.getGame().load(FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            fail();
        }
        final Kingdom kingAfterSave = Game.getGame().getCurrentKingdom();
        assertEquals(kingAfterSave.getExperienceAmount(), 0);
        try {
            final Set<Pair<Building, Boolean>> buildings = kingAfterSave.getComponents(Building.class);
            final Building building = this.getBuildingWithName(buildings, BUILDING_TEST);
            assertEquals(building.getLevel().getIncrementalLevel(), 2);
            assertEquals(kingAfterSave.getStatusStatistic().get(StatusName.HEALTH), Integer.valueOf(MAX_STATUS));
        } catch (Exception exc) {
            fail();
        }
    }
    /**
     * check if the component of building remain the same.
     */
    @Test
    public void testSaveAdvance() {
        Game.getGame().newGame(AgeUpKingdomStrategy.Type.SIMPLE);
        Kingdom kingdom = Game.getGame().getCurrentKingdom();
        Building.Container building = this.getBuildingWithName(kingdom.getComponents(Building.Container.class), BUILDING_NOT_ENABLE);
        kingdom.addExperience(EXPERIENCE);
        kingdom.nextAge();
        //the building image doesn't change
        ImageInfo im = building.getComponents(ImageInfo.class).stream().map(x -> x.getX()).findFirst().get();
        kingdom.addExperience(EXPERIENCE * EXPERIENCE);
        try {
            Game.getGame().save(FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
        kingdom.nextAge();
        //now the image of building change

        assertTrue(im.getPath().contains("1"));
        //check if the state of object remain consistent
        try {
            Game.getGame().load(FILE_NAME);
        } catch (ClassNotFoundException | IOException e) {
            fail();
        }
        kingdom = Game.getGame().getCurrentKingdom();
        building = this.getBuildingWithName(kingdom.getComponents(Building.Container.class), BUILDING_NOT_ENABLE);
        im = building.getComponents(ImageInfo.class).stream().map(x -> x.getX()).findFirst().get();
        assertNotNull(im);
    }
    /**
     * simple test for the kingdom.
     */
    @Test
    public void testKingdom() {
        Game.getGame().newGame(AgeUpKingdomStrategy.Type.SIMPLE);
        final Kingdom king = Game.getGame().getCurrentKingdom();
        //at the beginning the experience amount is equals to 0
        assertEquals(king.getExperienceAmount(), 0);
        //i can't go on the next level without experience
        assertFalse(king.canUpgradeAge());
        try {
            king.nextAge();
            fail("Can't go on next age");
        } catch (IllegalStateException exc) {
            assertNotNull(exc);
        }
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
        assertTrue(king.canUpgradeAge());
        king.nextAge();
        assertEquals(king.getExperienceAmount(), 0);
        //if i go in the next age the incremental value must be change
        assertNotSame(king.getAge().getIncrementalLevel(), 0);
        assertEquals(king.getAge().getIncrementalLevel(), 2);
        //at the beginning all stats are equal to zero
        king.getStatusStatistic().forEach((x, y) -> assertEquals(y, Integer.valueOf(0)));
        king.changeStatus(StatusName.HAPPINESS, MAX_STATUS);
        king.getStatusStatistic().forEach((x, y) -> {
            if (x == StatusName.HAPPINESS) {
                assertEquals(y, Integer.valueOf(100));
            }
        });
        king.changeStatus(StatusName.HAPPINESS, -MAX_STATUS);
        king.getStatusStatistic().forEach((x, y) -> assertEquals(y, Integer.valueOf(0)));
        king.addExperience(EXPERIENCE * EXPERIENCE);
        //try if there are some bugs in age
        while (king.getAge().isUpgradable()) {
            assertTrue(king.canUpgradeAge());
            king.nextAge();
        }
        try {
            king.nextAge();
            fail();
        } catch (IllegalStateException exc) { 
            assertNotNull(exc);
        }
    }
    private <E extends Building> E getBuildingWithName(final Set<Pair<E, Boolean>> building, 
                                                        final BuildingType name) {
        return building.stream().filter(x -> x.getX().getName().equals(name))
        .map(x -> x.getX())
        .findFirst().get();
    }
}

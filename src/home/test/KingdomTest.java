package home.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Test;

import home.model.building.Building;
import home.model.building.BuildingFactory;
import home.model.building.BuildingType;
import home.model.image.ImageComponent;
import home.model.kingdom.AgeUpKingdomStrategy;
import home.model.kingdom.Kingdom;
import home.model.kingdom.KingdomBuilder;
import home.model.level.Level;
import home.model.status.Status;
import home.model.status.StatusName;
import home.utility.Pair;

/**
 * some test on kingdom.
 */
public class KingdomTest {
    private static final int EXPERIENCE = 1000;
    private static final Level.Age START_AGE = Level.Age.createAgeLevel();
    private static final Status STATUS = Status.createSimpleStatus(StatusName.HAPPINESS);
    private static final int STATUS_SCORE = 100;
    //a method to create a simple builder
    private KingdomBuilder getBuilder() {
        return KingdomBuilder.createBuilder()
                             .addStatus(STATUS)
                             .setExperience(EXPERIENCE)
                             .setAge(Level.Age.createAgeLevel())
                             .addStrategy(AgeUpKingdomStrategy.Type.SIMPLE);
    }
    /**
     * Simple test on kingdom.
     */
    @Test
    public void simpleTest() {
        final Kingdom king = this.getBuilder().build();
        //test on age
        assertNotNull(king.getAge());
        assertTrue(king.canUpgradeAge());
        king.nextAge();
        assertFalse(king.canUpgradeAge());
        assertNotSame(king.getAge().getIncrementalLevel(), START_AGE.getIncrementalLevel());
        //test on experience
        try {
            king.addExperience(-EXPERIENCE);
            fail();
        } catch (IllegalArgumentException exc) {
            assertNotNull(exc);
        }
        try {
            king.decExperiene(EXPERIENCE);
            fail();
        } catch (IllegalArgumentException exc) {
            assertNotNull(exc);
        }
        king.addExperience(EXPERIENCE);
        assertTrue(king.getComponents().isEmpty());
        assertFalse(king.getStatusStatistic().isEmpty());
        assertFalse(king.changeStatus(StatusName.HEALTH, STATUS_SCORE));
        assertTrue(king.changeStatus(StatusName.HAPPINESS, STATUS_SCORE));
        try {
            king.changeStatus(StatusName.HAPPINESS, EXPERIENCE);
            fail();
        } catch (IllegalArgumentException exc) {
            assertNotNull(exc);
        }
        try {
            king.changeStatus(StatusName.HAPPINESS, -EXPERIENCE);
            fail();
        } catch (IllegalArgumentException exc) {
            assertNotNull(exc);
        }
        assertTrue(king.changeStatus(StatusName.HAPPINESS, -STATUS_SCORE));
    }
    /**
     * some test on builder.
     */
    @Test 
    public void builderTest() {
        final KingdomBuilder builder = this.getBuilder();
        builder.build();
        try {
            builder.setAge(START_AGE);
            fail();
        } catch (IllegalStateException exc) {
            assertNotNull(exc);
        }
        try {
            builder.setExperience(EXPERIENCE);
            fail();
        } catch (IllegalStateException exc) {
            assertNotNull(exc);
        }
        try {
            builder.addStatus(STATUS);
            fail();
        } catch (IllegalStateException exc) {
            assertNotNull(exc);
        }
        try {
            builder.addComponent(ImageComponent.createComponent("IMAGE"));
            fail();
        } catch (IllegalStateException exc) {
            assertNotNull(exc);
        }
    }
    /**
     * test on building.
     */
    @Test
    public void testKingdomBuilding() {
        final KingdomBuilder builder = this.getBuilder();
        BuildingFactory.get().createAllBuilding().forEach(x -> builder.addComponent(x));
        final Kingdom king = builder.build();
        final Building site = king.getComponents(Building.class).stream().map(x -> x.getX())
                                                                         .filter(x -> x.getName() == BuildingType.BUILDING_SITE)
                                                                         .findFirst().get();
        try {
            assertTrue(site.canLevelUp());
            site.levelUp();
            assertFalse(site.getLevel().isUpgradable());
            assertSame(king.getExperienceAmount(), 0);
        } catch (Exception exc) {
            fail("there is a building!");
        }
        //some building is blocked at the begging of game
        final int blockedBuilding = countBuilding(king.getComponents(Building.class));
        assertNotSame(blockedBuilding, 0);
        king.addExperience(EXPERIENCE);
        king.nextAge();
        //now some building is not blocked
        assertNotSame(blockedBuilding, countBuilding(this.getBuildings(king)));
        assertTrue(site.getLevel().isUpgradable());
    }
    /**
     * test advance kingdom.
     */
    @Test
    public void testKingdomAdvanced() {
        final KingdomBuilder builder = this.getBuilder();
        builder.addStrategy(AgeUpKingdomStrategy.Type.ADVANCED);
        BuildingFactory.get().createAllBuilding().forEach(x -> builder.addComponent(x));
        final Kingdom kingdom = builder.build();
        kingdom.addExperience(EXPERIENCE * EXPERIENCE);
        //the experience is not enough to age up, it check if all building are at the maximum level
        assertFalse(kingdom.canUpgradeAge());
        //now i try to level up all the building
        this.getBuildings(kingdom).stream()
                                  .filter(x -> x.getY())
                                  .map(x -> x.getX())
                                  .forEach(x -> {
                                      try {
                                          x.levelUp();
                                      } catch (IllegalStateException exc) {
                                          fail();
                                      }
                                  });
        assertTrue(kingdom.canUpgradeAge());
        try {
            kingdom.nextAge();
        } catch (IllegalStateException exc) {
            fail();
        }
    }
    private <E> Set<Pair<Building, Boolean>> getBuildings(final Kingdom king) {
        return king.getComponents(Building.class);
    }

    private int countBuilding(final Set<Pair<Building, Boolean>> building) {
        return (int) building.stream().filter(x -> !x.getY())
                .count();
    }
}

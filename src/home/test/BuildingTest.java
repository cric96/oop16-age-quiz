package home.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Test;

import home.model.building.Building;
import home.model.building.BuildingFactory;
import home.model.building.BuildingOfKingdom;
import home.model.building.BuildingType;
import home.model.composite.Component;
import home.model.image.ImageComponent;
import home.model.image.ImageInfo;
import home.model.level.Level;
import home.model.query.Category;

/**
 * the test on building.
 */
public class BuildingTest {
    /**
     * 
     */
    @Test
    public void testSimpleBuilding() {
        final Building building = BuildingFactory.get().createSimpleBuilding(BuildingType.ACADEMY);
        assertEquals(building.getInfluecedCategory(), Category.LIBERAL_ARTS);
        try {
            building.levelUp();
            fail();
        } catch (IllegalStateException exc) { 
            assertNotNull(exc);
        }
        assertEquals(building.getLevel().getIncrementalLevel(), 1);
        try {
            assertTrue(building.canLevelUp());
            fail();
        } catch (IllegalStateException e) {
            assertNotNull(e);
        }
    }
    /**
     * 
     */
    @Test
    public void testSetBuilding() {
        final Set<BuildingOfKingdom> buildings = BuildingFactory.get().createAllBuilding();
        final int sizeSet = buildings.size();
        final int numBuilding = BuildingType.values().length;
        assertEquals(numBuilding, sizeSet);
        BuildingOfKingdom building;
        try {
            building = buildings.stream().findFirst().get();
            building.addComponent(BuildingFactory.get().createSimpleBuilding(BuildingType.HOSPITAL));
            fail();
        } catch (IllegalStateException e) {
            assertNotNull(e);
        }  catch (Exception e) {
            fail();
        }
    }
    /**
     * advance test on container building.
     */
    @Test
    public void testAdvanceBuilding() {
        final BuildingOfKingdom building = BuildingFactory.get().createSimpleBuilding(BuildingType.ACADEMY);
        final ImageComponent image = ImageComponent.createComponent(BuildingType.ACADEMY.name());
        Component.compositeAttach(building, image);
        assertFalse(building.getComponents(ImageInfo.class).isEmpty());
        assertTrue(building.getComponents(ImageComponent.class).isEmpty());
        assertFalse(building.getParent().isPresent());
        final int maxLevel = 3;
        final int current = 1;
        final int exp = 1000;
        final BuildingOfKingdom buildingRestored = BuildingFactory.get().createAdvanceBuilding(BuildingType.BUILDING_SITE, 
                                    Level.Building.restoreBuildingLevel(current, maxLevel, exp, exp));
        final Level lv = buildingRestored.getLevel();
        assertTrue(lv.isUpgradable());
        assertEquals(lv.getExperienceAmount(), exp);
        assertEquals(lv.getReachMaximumLevel(), maxLevel);
    }
}

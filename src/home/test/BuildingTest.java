package home.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ResourceBundle;
import java.util.Set;

import org.junit.Test;

import home.model.building.BuildingComposite;
import home.model.building.BuildingFactory;
import home.model.building.BuildingType;
import home.model.building.ImmutableAgeBuilding;
import home.model.composite.Component;
import home.model.image.ImageComponent;
import home.model.image.ImageInfo;
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
        final ImmutableAgeBuilding building = BuildingFactory.get().createSimpleBuilding(BuildingType.ACADEMY);
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
        final Set<BuildingComposite> buildings = BuildingFactory.get().createAllBuilding();
        assertSame(buildings.size(), BuildingType.values().length);
        BuildingComposite building;
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
        final ImmutableAgeBuilding.Container building = BuildingFactory.get().createSimpleBuilding(BuildingType.ACADEMY);
        final ImageComponent image = ImageComponent.createComponent(BuildingType.ACADEMY.toString());
        Component.compositeAttach(building, image);
        assertFalse(building.getComponents(ImageInfo.class).isEmpty());
        assertTrue(building.getComponents(ImageComponent.class).isEmpty());
    }
}

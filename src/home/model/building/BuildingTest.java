package home.model.building;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import home.model.composite.Component;
import home.model.image.ImageComponent;
import home.model.image.ImageInfo;

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
        assertEquals(building.getInfluecedCategory(), BuildingType.ACADEMY.getCategory());
        try {
            building.levelUp();
            fail();
        } catch (IllegalStateException exc) { 
            assertNotNull(exc);
        }
        assertEquals(building.getLevel().getIncrementalLevel(), 1);
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

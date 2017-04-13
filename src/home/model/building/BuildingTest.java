package home.model.building;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * the test on building.
 */
public class BuildingTest {
    //TODO QUANDO AVRAI FATTO UN COMPONENT CREARE UN TEST AVANZATO
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
}

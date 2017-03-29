package home.model.building;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import home.model.level.Level;
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
        } catch (IllegalStateException exc) { }
        assertEquals(building.getLevel().getIncrementalLevel(), 0);
        
    }
}

package home.model.building;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import home.model.level.Level;
/**
 * the test on building.
 */
public class BuildingTest {
    /**
     * 
     */
    @Test
    public void testBuilding() {
        final Level.Building level = Level.Building.createBuildingLevel();
        final Building building = BuildingFactory.get()
                                           .createSimpleBuilding(BuildingType.HANDWOOD,
                                                                 level,
                                                                 0);
        //In general a building could be upgrade when it is instatiate
        assertTrue(building.isUpgradable());
        //a the beginning a building have level equals to 0
        assertEquals(building.getLevel(), 0);
        assertEquals(building.levelUp(level.getExperienceAmount() - 1), Optional.empty());
        assertNotEquals(building.levelUp(level.getExperienceAmount()), Optional.empty());
        //by default the maxium level that a building can reach is 1
        assertFalse(building.isUpgradable());
        assertSame(building.getInfluecedCategory(), BuildingType.HANDWOOD.getCategory());
        building.nextAge();
        //when building go on the next age the level maxium value change
        assertTrue(building.isUpgradable());
        assertSame(building.getLevel(), 1);
    }
}

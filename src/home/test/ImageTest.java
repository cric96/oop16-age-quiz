package home.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import home.model.Game;
import home.model.building.BuildingType;
import home.model.image.ImageComponent;
import home.model.kingdom.AgeUpKingdomStrategy;
import home.model.kingdom.Kingdom;
/**
 * some simple test on image.
 */
public class ImageTest {
    private static final int EXPERIENCE = 1000;
    /**
     * check if the name change when change the age.
     */
    @Test
    public void basicTest() {
        final ImageComponent component = ImageComponent.createComponent(BuildingType.ACADEMY.name());
        Game.getGame().newGame(AgeUpKingdomStrategy.Type.SIMPLE);
        final Kingdom king = Game.getGame().getCurrentKingdom();
        component.attachOn(king);
        king.addComponent(component);
        king.addExperience(EXPERIENCE);
        king.nextAge();
        assertTrue(component.getPath().contains("1"));
        assertEquals(component.getParent().get(), king);
    }
}

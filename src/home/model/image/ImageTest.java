package home.model.image;

import org.junit.Test;

import home.model.building.BuildingType;
import home.model.Game;
import home.model.Kingdom;
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
        System.out.println(component.getPath());
        Game.getGame().newGame();
        final Kingdom king = Game.getGame().getCurrentKingdom();
        component.attachOn(king);
        king.addComponent(component);
        king.addExperience(EXPERIENCE);
        king.nextAge();
        System.out.println(component.getPath());
    }
}

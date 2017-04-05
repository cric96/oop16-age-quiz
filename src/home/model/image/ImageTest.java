package home.model.image;

import org.junit.Test;

import home.model.building.BuildingType;
import home.model.composite.Event;
/**
 * some simple test on image.
 */
public class ImageTest {
    /**
     * check if the name change when change the age.
     */
    @Test
    public void basicTest() {
        ImageComponent component = ImageComponent.createComponent(BuildingType.ACADEMY.name());
        System.out.println(component.getPath());
    }
}

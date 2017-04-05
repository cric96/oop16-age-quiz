package home.model.image;
import home.model.composite.Component;
import home.model.composite.Composite;
/**
 * a component that define has an image.
 */
public interface ImageComponent extends Image, Component<Composite> {
    /**
     * create a simple ImageComponent.
     * @param name
     *  the name of the image
     * @return
     *  the component created
     */
    static ImageComponent createComponent(final String name) {
        return new ImageComponentImpl(name);
    }
}

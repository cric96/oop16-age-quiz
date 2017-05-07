package home.model.image;

import home.model.composite.AbstractComponent;
import home.model.composite.Composite;
import home.model.composite.Event;
import home.model.composite.EventType;
//package protected
final class ImageComponentImpl extends AbstractComponent<Composite> implements ImageComponent {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String EXTENSION = ".png";
    private static final String RES_SEPARATOR = "/";
    private static final String FOLDER = "images";
    private final String name;
    private int currentImage;
    ImageComponentImpl(final String name) {
        this.name = name;
        this.currentImage = 0;
    }
    @Override
    public Class<?> getType() {
        return ImageInfo.class;
    }
    @Override
    public void update(final Event<?> event) {
        if (event.getTypes().equals(EventType.AGE_CHANGE.name())) {
            this.currentImage++;
        }
    }
    @Override
    public String getPath() {
        return new String(RES_SEPARATOR + FOLDER + RES_SEPARATOR + this.name + (this.currentImage == 0 ? "" : this.currentImage) + EXTENSION);
    }
    @Override
    public String getExtension() {
        return EXTENSION;
    }
    @Override
    public String toString() {
        return "ImageComponentImpl [name=" + name + ", currentImage=" + currentImage + "]";
    }
}

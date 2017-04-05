package home.model.image;

import java.io.File;

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
    private static final String ROOT = "res";
    private static final String EXTENSION = ".png";
    private final String name;
    private int currentImage;
    ImageComponentImpl(final String name) {
        this.name = name;
        this.currentImage = 0;
    }
    @Override
    public Class<?> getType() {
        return Image.class;
    }
    @Override
    public void update(final Event<?> event) {
        if (event.getTypes().equals(EventType.AGE_CHANGE.name())) {
            this.currentImage++;
        }
    }
    @Override
    public File getPath() {
        return new File(ROOT + System.getProperty("file.separator") + this.name + this.currentImage + EXTENSION);
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

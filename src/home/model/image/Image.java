package home.model.image;

import java.io.File;

/**
 * an interface to define an image.
 */
public interface Image {
    /**
     * 
     * @return
     *  where the image is located
     */
    File getPath();
    /**
     * 
     * @return
     *  the extension of the image
     */
    String getExtension();
}

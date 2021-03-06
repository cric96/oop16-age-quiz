package home.model.image;
/**
 * an interface to define an image.
 */
public interface ImageInfo {
    /**
     * 
     * @return
     *  where the image is located
     */
    String getPath();
    /**
     * 
     * @return
     *  the extension of the image
     */
    String getExtension();
}

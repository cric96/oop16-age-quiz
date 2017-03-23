package home.model.building;

import java.io.File;

/**
 * the image of a building.
 * when an image go on the next age an incremental value concat on the end
 * if the image is not save throw new IllegalStateException
 */
public interface BuildingImage extends AgeComponent {
    /**
     * 
     * @param path
     *  the path of the image
     * @return
     *  the component created
     */
    static BuildingImage createBuildingImage(final File path) {
        return new BuildingImageImpl(path);
    }
    /**
     * @return
     *  the path of the current image
     */
    File getImagePath();
}

package home.model.building;

import java.io.File;

import home.model.composite.Component;
import home.model.composite.Composite;

/**
 * the image of a building.
 * when an image go on the next age an incremental value concat on the end
 * if the image is not save throw new IllegalStateException
 */
public interface BuildingImage extends Component<Composite> {
    /*TODO DA RIFARE*/
    /**
     * @return
     *  the path of the current image
     */
    File getImagePath();
}

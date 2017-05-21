package home.view.world.fx;

import home.utility.ResourceManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * super class for all building buttons or kingdom button.
 */
class BuildingButton extends ImageView {
    private static final int BUILDING_SIZE = 200;
    /**
     * @param graphicPath 
     *              the image path of the button
     */
    BuildingButton(final String graphicPath) {
        super(new Image(ResourceManager.load(graphicPath).toExternalForm()));
        this.setSize();
    }

    private void setSize() {
        this.setFitWidth(BUILDING_SIZE);
        this.setFitHeight(BUILDING_SIZE);
    }
}

package home.view.world.fx;

import java.io.IOException;
import java.util.Optional;

import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
import home.model.building.BuildingType;
import home.utility.ResourceManager;
import home.view.fx.Images;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;

/**
 * class who implement a dialog to start quiz or upgrade a building.
 */
public class ParentDialog extends Parent {

    /**
     * create a parent for a general building dialog.
     * @param controller 
     * @param building 
     * @param dialog 
     * @param pop 
     */
    public ParentDialog(final WorldObserver controller, final Optional<BuildingType> building, final Dialog dialog, final Popup pop) {
        super();
        final double cloudWidthProp = 1.3;
        final double cloudHeightProp = 1.7;
        final String path = ResourceManager.load(Images.CLOUD_PANE.getPath()).toExternalForm();
        final Image img = new Image(path);
        final ImageView cloud = new ImageView(img);
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("buildingInfoDialog.fxml"));
        FXMLInfoBuildingController c = new FXMLInfoBuildingController();
        try {
            final Parent p = fxmlLoader.load();
            this.getChildren().add(p);
            cloud.setFitWidth(this.getLayoutBounds().getWidth() * cloudWidthProp);
            cloud.setFitHeight(this.getLayoutBounds().getHeight() * cloudHeightProp);
            this.getChildren().clear();
            this.getChildren().addAll(cloud, p);
            c = fxmlLoader.<FXMLInfoBuildingController>getController();
            c.setBuildingController(controller, building);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (building.isPresent()) {
            c.setStart(true);
        } else {
            c.setStart(false);
        }
        c.setPop(pop);
        c.setUpgrade(!dialog.levelUpEnabled());
        c.setExperience(dialog.getExperience());
        c.setLevel(dialog.getLevel());
        c.setName(dialog.getName());
    }
}

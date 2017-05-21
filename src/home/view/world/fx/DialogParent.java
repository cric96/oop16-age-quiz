package home.view.world.fx;

import java.util.ResourceBundle;

import home.controller.dialog.Dialog;
import home.utility.BundleLanguageManager;
import home.utility.Bundles;
import home.utility.ResourceManager;
import home.view.fx.FXMLFiles;
import home.view.fx.FxmlResourceManager;
import home.view.fx.Images;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;

/**
 * class who implement a dialog to start quiz or upgrade a building.
 */
class DialogParent extends Parent {
    private final ResourceBundle bundle = BundleLanguageManager.get().getBundle(Bundles.LABEL);
    private final FXMLInfoBuilding fxmlController = new FXMLInfoBuilding();

    /**
     * create a parent for a general building dialog.
     * @param controller 
     * @param building 
     * @param dialog 
     * @param pop 
     */
    DialogParent(final Dialog dialog, final Popup pop) {
        final double cloudWidthProp = 1.3;
        final double cloudHeightProp = 1.7;
        final String path = ResourceManager.load(Images.CLOUD_PANE.getPath()).toExternalForm();
        final Image img = new Image(path);
        final ImageView cloud = new ImageView(img);
        final FxmlResourceManager fxmlManager = new FxmlResourceManager(FXMLFiles.BUILDING_INFO, this.fxmlController);
        final Parent parent = fxmlManager.load();
        this.getChildren().add(parent);
        cloud.setFitWidth(this.getLayoutBounds().getWidth() * cloudWidthProp);
        cloud.setFitHeight(this.getLayoutBounds().getHeight() * cloudHeightProp);
        this.getChildren().clear();
        this.getChildren().addAll(cloud, parent);
        fxmlController.setPop(pop);
        fxmlController.setExperience(dialog.getExperience());
        fxmlController.setLevel(dialog.getLevel());
        fxmlController.setName(dialog.getName());
    }

    protected ResourceBundle getBundle() {
        return this.bundle;
    }

    protected FXMLInfoBuilding getController() {
        return this.fxmlController;
    }
}

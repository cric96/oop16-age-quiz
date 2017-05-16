package home.view.world.fx;

import java.io.IOException;

import home.controller.observer.WorldObserver;
import home.utility.ResourceManager;
import home.utility.UtilityScreen;
import home.view.fx.CustomParent;
import home.view.fx.Images;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * concrete realization of world in javafx.
 *
 */
final class ParentWorld extends CustomParent {
    private FXMLControllerWorld fxmlController;
    private static final double OPACITY = 0.4;

    /**
     * @throws IOException if the background load gone wrong.
     * @param controller 
     */
    ParentWorld(final WorldObserver controller) {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("base.fxml"));
        final VBox pane = new VBox();
        pane.setPrefSize(UtilityScreen.getScreenWidth(), UtilityScreen.getScreenHeight());
        fxmlLoader.setRoot(pane);
        try {
            fxmlLoader.load();
            fxmlController = fxmlLoader.<FXMLControllerWorld>getController();
            fxmlController.setController(controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String fileName = ResourceManager.load(Images.WORLD_BACKGROUND.getPath()).toExternalForm();
        final ImageView background = new ImageView(new Image(fileName));
        background.setFitWidth(UtilityScreen.getScreenWidth());
        background.setFitHeight(UtilityScreen.getScreenHeight());
        final Rectangle bg = new Rectangle(UtilityScreen.getScreenWidth(), UtilityScreen.getScreenHeight());
        bg.setFill(Color.BLACK);
        bg.setOpacity(OPACITY);
        this.getChildren().addAll(background, bg, pane);
    }

    /**
     * @return the c
     */
    public FXMLControllerWorld getFxmlControllerWorld() {
        return fxmlController;
    }
}

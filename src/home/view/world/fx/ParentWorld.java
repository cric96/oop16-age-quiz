package home.view.world.fx;

import java.io.IOException;

import home.controller.observer.WorldObserver;
import home.utility.ResourceManager;
import home.utility.view.UtilityScreen;
import home.view.fx.CustomParent;
import home.view.fx.Images;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * concrete realization of the parent world in javafx.
 */
final class ParentWorld extends CustomParent {
    private final FXMLControllerWorld fxmlController = new FXMLControllerWorld();
    private static final double OPACITY = 0.4;

    /**
     * @throws IOException
     *             if the background load gone wrong.
     * @param controller
     */
    ParentWorld(final WorldObserver controller) {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ParentWorld.class.getResource("base.fxml"));
        this.fxmlController.setController(controller);
        loader.setController(this.fxmlController);
        final String fileName = ResourceManager.load(Images.WORLD_BACKGROUND.getPath()).toExternalForm();
        final ImageView background = new ImageView(new Image(fileName));
        final Rectangle bg = new Rectangle(UtilityScreen.getScreenWidth(), UtilityScreen.getScreenHeight());
        try {
            final Parent parent = loader.load();
            ((Region) parent).setPrefSize(UtilityScreen.getScreenWidth(), UtilityScreen.getScreenHeight());
            this.getChildren().addAll(background, bg, parent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        background.setFitWidth(UtilityScreen.getScreenWidth());
        background.setFitHeight(UtilityScreen.getScreenHeight());
        bg.setFill(Color.BLACK);
        bg.setOpacity(OPACITY);
    }

    /**
     * @return the fxmlController (used by FXWorldView to set graphic elements)
     */
    public FXMLControllerWorld getFxmlControllerWorld() {
        return fxmlController;
    }
}

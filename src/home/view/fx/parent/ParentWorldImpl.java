package home.view.fx.parent;

import java.io.IOException;

import home.controller.observer.WorldObserver;
import home.utility.ResourceManager;
import home.utility.view.UtilityScreen;
import home.view.fx.FXMLFiles;
import home.view.fx.FxmlResourceManager;
import home.view.fx.Images;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * concrete realization of the parent world in javafx.
 */
final class ParentWorldImpl extends CustomParentImpl implements ParentFXML {
    private final FXMLControllerWorldImpl fxmlController = new FXMLControllerWorldImpl();
    private static final double OPACITY = 0.4;

    /**
     * @throws IOException
     *             if the background load gone wrong.
     * @param controller
     */
    ParentWorldImpl(final WorldObserver controller) {
        final FxmlResourceManager fxmlManager = new FxmlResourceManager(FXMLFiles.WORLD, fxmlController);
        fxmlController.setController(controller);
        final String fileName = ResourceManager.load(Images.WORLD_BACKGROUND.getPath()).toExternalForm();
        final ImageView background = new ImageView(new Image(fileName));
        final Rectangle bg = new Rectangle(UtilityScreen.getScreenWidth(), UtilityScreen.getScreenHeight());
        final Parent parent = fxmlManager.load();
        ((Region) parent).setPrefSize(UtilityScreen.getScreenWidth(), UtilityScreen.getScreenHeight());
        this.getChildren().addAll(background, bg, parent);
        background.setFitWidth(UtilityScreen.getScreenWidth());
        background.setFitHeight(UtilityScreen.getScreenHeight());
        bg.setFill(Color.BLACK);
        bg.setOpacity(OPACITY);
    }

    @Override
    public FXMLController getFxmlControllerWorld() {
        return fxmlController;
    }
}

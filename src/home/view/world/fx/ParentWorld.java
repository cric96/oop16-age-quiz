package home.view.world.fx;

import java.io.IOException;
import home.controller.WorldController;
import home.utility.ResourceManager;
import home.utility.UtilityScreen;
import home.view.fx.Images;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * concrete realization of world in javafx.
 *
 */
public class ParentWorld extends Parent {
    private FXMLControllerWorld c;
    private static final double OPACITY = 0.6;

    /**
     * @throws IOException if the background load gone wrong.
     * @param controller 
     */
    public ParentWorld(final WorldController controller) {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("base.fxml"));
        final VBox pane = new VBox();
        pane.setPrefSize(UtilityScreen.getScreenWidth(), UtilityScreen.getScreenHeight());
        fxmlLoader.setRoot(pane);
        try {
            fxmlLoader.load();
            c = fxmlLoader.<FXMLControllerWorld>getController();
            c.setController(controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String fileName = ResourceManager.load(Images.WORLD_BACKGROUND.getPath()).toExternalForm();
        final Image img = new Image(fileName);
        final ImageView background = new ImageView(img);
        background.setFitWidth(UtilityScreen.getScreenWidth());
        background.setFitHeight(UtilityScreen.getScreenHeight());
        final Rectangle bg = new Rectangle(UtilityScreen.getScreenWidth(), UtilityScreen.getScreenHeight());
        bg.setFill(Color.GREY);
        bg.setOpacity(OPACITY);
        this.getChildren().addAll(background, bg, pane);
    }

    /**
     * @return the c
     */
    public FXMLControllerWorld getFxmlControllerWorld() {
        return c;
    }
}

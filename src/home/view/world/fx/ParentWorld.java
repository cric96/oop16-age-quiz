package home.view.world.fx;

import java.io.IOException;

import home.controller.WorldController;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

/**
 * concrete realization of world in javafx.
 *
 */
public class ParentWorld extends Parent {
    private final Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

    /**
     * @throws IOException if the background load gone wrong.
     * @param controller 
     */
    public ParentWorld(final WorldController controller) {
        final Rectangle bg = new Rectangle(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        bg.setFill(Color.WHITE);
        this.getChildren().add(bg);
    }
}

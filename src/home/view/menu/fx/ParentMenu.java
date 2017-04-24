package home.view.menu.fx;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import home.controller.MenuController;
import home.view.fx.AbstractFXView;
import home.view.fx.FXMessageType;
import home.view.menu.Buttons;
import home.view.menu.MainMenuImpl;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.stage.Screen;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * concrete realization of menu in javafx.
 *
 */
public class ParentMenu extends Parent {
    private final Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private static final int X_TRANSLATE = 100;
    private static final int Y_TRANSLATE = 200;
    private static final int BOX = 15;
    private static final int TITLE_SIZE = 75;
    private static final double OPACITY = 0.4;

    /**
     * @throws IOException if the background load gone wrong.
     * @param controller 
     */
    public ParentMenu(final MenuController controller) {
        final VBox menuZero = new VBox(BOX);
        menuZero.setTranslateX(X_TRANSLATE);
        menuZero.setTranslateY(Y_TRANSLATE);

        final Text text = new Text(MainMenuImpl.getTitle());
        text.setFont(new Font(TITLE_SIZE));
        menuZero.getChildren().add(text);
        final MenuButton btnNewGame = new MenuButton(Buttons.NEW_GAME.getText(), Color.BLACK);
        btnNewGame.setOnMouseClicked(e -> {
            controller.newGamePressed();
        });

        final MenuButton btnLoadGame = new MenuButton(Buttons.LOAD_GAME.getText(), Color.BLACK);
        btnLoadGame.setOnMouseClicked(e -> {
            controller.loadGamePressed();
        });

        final MenuButton btnExit = new MenuButton(Buttons.EXIT.getText(), Color.RED);
        btnExit.setOnMouseClicked(e -> {
            controller.exitPressed();
            AbstractFXView.showMessage("Are you sure do this?", FXMessageType.EXIT);
        });

        menuZero.getChildren().addAll(btnNewGame, btnLoadGame, btnExit);

        InputStream is;
        try {
            is = Files.newInputStream(Paths.get(MainMenuImpl.backgroundPath()));
            final Image img = new Image(is);
            is.close();
            final ImageView imgView = new ImageView(img);
            imgView.setFitWidth(primaryScreenBounds.getWidth());
            imgView.setFitHeight(primaryScreenBounds.getHeight());
            getChildren().add(imgView);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        final Rectangle bg = new Rectangle(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        bg.setFill(Color.GREY);
        bg.setOpacity(OPACITY);

        getChildren().addAll(bg, menuZero);
    }
}

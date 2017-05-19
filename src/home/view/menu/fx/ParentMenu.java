package home.view.menu.fx;

import java.io.IOException;

import home.controller.observer.MenuObserver;
import home.utility.ResourceManager;
import home.utility.Utility;
import home.utility.UtilityScreen;
import home.view.Fonts;
import home.view.fx.CustomParent;
import home.view.fx.Images;
import home.view.menu.Buttons;
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
class ParentMenu extends CustomParent {
    private static final int X_TRANSLATE = 100;
    private static final int Y_TRANSLATE = 200;
    private static final int BOX = 15;
    private static final int TITLE_SIZE = 90;
    private static final double OPACITY = 0.4;

    /**
     * @throws IOException if the background load gone wrong.
     * @param controller 
     */
    ParentMenu(final MenuObserver controller) {
        final VBox menuZero = new VBox(BOX);
        menuZero.setTranslateX(X_TRANSLATE);
        menuZero.setTranslateY(Y_TRANSLATE);
        final Text text = new Text(Utility.getTitle());
        final String font = ResourceManager.load(Fonts.FAITH_COLLAPSING.getFontPath()).toExternalForm();
        text.setFont(Font.loadFont(font, TITLE_SIZE));
        menuZero.getChildren().add(text);
        final MenuButton btnNewGame = new MenuButton(Buttons.NEW_GAME.toString(), Color.BLACK);
        btnNewGame.setOnMouseClicked(e -> {
            controller.newGamePressed();
        });

        final MenuButton btnLoadGame = new MenuButton(Buttons.LOAD_GAME.toString(), Color.BLACK);
        btnLoadGame.setOnMouseClicked(e -> {
            controller.loadGamePressed();
        });

        final MenuButton btnExit = new MenuButton(Buttons.EXIT.toString(), Color.RED);
        btnExit.setOnMouseClicked(e -> {
            controller.exitPressed();
        });
        menuZero.getChildren().addAll(btnNewGame, btnLoadGame, btnExit);
        String fileName;
        fileName = ResourceManager.load(Images.MENU_BACKGROUND.getPath()).toExternalForm();
        final Image img = new Image(fileName);
        final ImageView imgView = new ImageView(img);
        imgView.setFitWidth(UtilityScreen.getScreenWidth());
        imgView.setFitHeight(UtilityScreen.getScreenHeight());
        getChildren().add(imgView);
        final Rectangle bg = new Rectangle(UtilityScreen.getScreenWidth(), UtilityScreen.getScreenHeight());
        bg.setFill(Color.GREY);
        bg.setOpacity(OPACITY);
        getChildren().addAll(bg, menuZero);
    }
}

package home.view.menu.fx;

import java.io.IOException;
import home.controller.observer.MenuObserver;
import home.utility.ResourceManager;
import home.utility.Utility;
import home.utility.view.UtilityScreen;
import home.view.Fonts;
import home.view.fx.CustomParent;
import home.view.fx.Images;
import home.view.menu.Buttons;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private final VBox menuZero = new VBox(BOX);
    private final MenuButton btnNewGame;
    private final MenuButton btnLoadGame;
    private final MenuButton btnExit;

    /**
     * @throws IOException if the background load gone wrong.
     * @param controller 
     */
    ParentMenu(final MenuObserver controller) {
        final BorderPane rootPane = new BorderPane();
        rootPane.setLeft(menuZero);
        menuZero.setTranslateX(X_TRANSLATE);
        menuZero.setTranslateY(Y_TRANSLATE);
        final Text text = new Text(Utility.getTitle());
        final String font = ResourceManager.load(Fonts.FAITH_COLLAPSING.getFontPath()).toExternalForm();
        text.setFont(Font.loadFont(font, TITLE_SIZE));
        menuZero.getChildren().add(text);
        btnNewGame = new MenuButton(Buttons.NEW_GAME.toString(), Color.BLACK);
        btnNewGame.setOnMouseClicked(e -> {
            controller.newGamePressed();
        });

        btnLoadGame = new MenuButton(Buttons.LOAD_GAME.toString(), Color.BLACK);
        btnLoadGame.setOnMouseClicked(e -> {
            controller.loadGamePressed();
        });

        btnExit = new MenuButton(Buttons.EXIT.toString(), Color.RED);
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
        final HBox languageButtonBox = new LanguageBox(this);
        languageButtonBox.setAlignment(Pos.BOTTOM_RIGHT);
        rootPane.setBottom(languageButtonBox);
        rootPane.setPrefSize(UtilityScreen.getScreenWidth(), UtilityScreen.getScreenHeight());
        getChildren().addAll(bg, rootPane);
    }

    /**
     * repaint the button in the menu.
     */
    public final void repaint() {
        btnLoadGame.setText(Buttons.LOAD_GAME.toString());
        btnNewGame.setText(Buttons.NEW_GAME.toString());
        btnExit.setText(Buttons.EXIT.toString());
    }
}

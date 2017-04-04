package home.view.menu;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import home.view.MainView;
import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameMenu extends Parent {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;
    private static final int X_TRANSLATE = 100;
    private static final MainMenuImpl MAIN_MENU = new MainMenuImpl();
    private static final int Y_TRANSLATE = 200;
    private static final int BOX = 15;
    private static final int TITLE_SIZE = 45;
    private static final double OPACITY = 0.4;
    private final List<String> listName = MAIN_MENU.buttonsNameList();
    private Set<MenuButton> setButton = new HashSet<>();
    private Rectangle bg;

    public GameMenu(final MainView menu) {
        VBox menuZero = new VBox(BOX);
        VBox menuOne = new VBox(BOX);
        menuZero.setTranslateX(X_TRANSLATE);
        menuZero.setTranslateY(Y_TRANSLATE);

        menuOne.setTranslateX(X_TRANSLATE);
        menuOne.setTranslateY(Y_TRANSLATE);

        Text text = new Text(MAIN_MENU.getTitle());
        text.setFont(new Font(TITLE_SIZE));
        menuZero.getChildren().add(text);

        for (int i = 0; i < MAIN_MENU.buttonsNameList().size(); i++) {
            MenuButton btn = new MenuButton(listName.get(i), Color.WHITE);
            btn.setOnMouseClicked(e -> {
                FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.setOnFinished(x -> this.setVisible(false));
                ft.play();
            });
            setButton.add(btn);
        }

        MenuButton btnExit = new MenuButton(listName.get(listName.size() - 1), Color.RED);
        btnExit.setOnMouseClicked(e -> {
        });

        menuZero.getChildren().addAll(setButton);
        menuZero.getChildren().add(btnExit);

        bg = new Rectangle(WINDOW_WIDTH, WINDOW_HEIGHT);
        bg.setFill(Color.GREY);
        bg.setOpacity(OPACITY);

        getChildren().addAll(bg, menuZero);
    }


}

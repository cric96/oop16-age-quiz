package home.view.menu;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.animation.FadeTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.stage.Screen;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameMenu extends Parent {
    private final Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    private static final int X_TRANSLATE = 100;
    private static final int Y_TRANSLATE = 200;
    private static final int BOX = 15;
    private static final int TITLE_SIZE = 45;
    private static final double OPACITY = 0.4;
    private final List<String> listName = MainMenuImpl.buttonsNameList();
    private final Set<MenuButton> setButton = new HashSet<>();
    private final Rectangle bg;

    public GameMenu() throws IOException {
        VBox menuZero = new VBox(BOX);
        VBox menuOne = new VBox(BOX);
        menuZero.setTranslateX(X_TRANSLATE);
        menuZero.setTranslateY(Y_TRANSLATE);

        menuOne.setTranslateX(X_TRANSLATE);
        menuOne.setTranslateY(Y_TRANSLATE);

        Text text = new Text(MainMenuImpl.getTitle());
        text.setFont(new Font(TITLE_SIZE));
        menuZero.getChildren().add(text);

        for (int i = 0; i < MainMenuImpl.buttonsNameList().size() - 1 ; i++) {
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

        final MenuButton btnExit = new MenuButton(listName.get(listName.size() - 1), Color.RED);
        btnExit.setOnMouseClicked(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("Are you sure do this?");
            final Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                System.exit(0);
            }
        });

        menuZero.getChildren().addAll(setButton);
        menuZero.getChildren().add(btnExit);
        final InputStream is = Files.newInputStream(Paths.get(MainMenuImpl.backgroundPath()));
        final Image img = new Image(is);
        is.close();
        final ImageView imgView = new ImageView(img);
        imgView.setFitWidth(primaryScreenBounds.getMaxX());
        imgView.setFitHeight(primaryScreenBounds.getMaxY());
        bg = new Rectangle(primaryScreenBounds.getMaxX(), primaryScreenBounds.getMaxY());
        bg.setFill(Color.GREY);
        bg.setOpacity(OPACITY);

        getChildren().addAll(imgView, bg, menuZero);
    }


}

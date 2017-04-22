package home.view;

import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class FXViewImpl implements FXView {
    private Scene scene;

    @Override
    public Scene getScene() {
        return this.scene;
    }
}

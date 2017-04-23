package home.view;

import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public abstract class FXViewImpl implements FXView {
    private Optional<Scene> scene;

    public FXViewImpl() {
        this.scene = Optional.empty();
    }
    @Override
    public Scene getScene() {
        return scene.get();
    }
   protected void setScene(Scene scene){
        this.scene = Optional.ofNullable(scene);
    }
}

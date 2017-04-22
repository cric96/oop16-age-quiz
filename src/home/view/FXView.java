package home.view;

import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public interface FXView{
    /***
     * @return Scene the actual scene of the view.
     */
    Scene getScene();
}

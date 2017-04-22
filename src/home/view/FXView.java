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

    /**
     * display specific dialog with a message for the user. 
     * @param message to show.
     * @param type is used to show different window message.
     * @return 
     */
    static void showMessage(String message, FXMessageType type) {
        Alert alert = new Alert(type.getAlertType());
        FXContainer.getContainer().showDialog(alert);
        alert.setTitle(type.getMessageTitle());
        alert.setHeaderText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK && type.equals(FXMessageType.EXIT)){
            System.exit(0);
        }
    }

}

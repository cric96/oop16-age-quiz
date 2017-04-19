package home.view;

import java.util.Optional;

import home.controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Generic View Interface.
 *
 * @param <E>
 */
public interface View <E extends Controller> {
    /**
     * method used to attach a view to a specific controller.
     * @param controller represent the controller.
     */
    void attachOn(E controller);


    /**
     * display specific dialog with a message for the user. 
     * @param message to show.
     * @param type is used to show different window message.
     */
    static void showMessage(String message, MessageType type){
        Alert alert = new Alert(type.getAlertType());
        alert.setTitle(type.getMessageTitle());
        alert.setHeaderText(message);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK && type.equals(MessageType.EXIT)){
            System.exit(0);
        }
    }
}

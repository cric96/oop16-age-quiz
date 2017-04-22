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

}

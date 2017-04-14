package home.view;

import home.controller.Controller;

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
     * display specific dialog with a message for the user 
     * @param message to show.
     * @param type is used to show different window message.
     */
    static void showMessage(String message, MessageType type) {

    }
}

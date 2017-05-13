package home.view;

import home.controller.observer.Observer;


/**
 * Generic View Interface.
 *
 * @param <E>
 */
public interface View <E extends Observer> {
    /**
     * method used to attach a view to a specific controller.
     * 
     * @param controller
     *            represent the controller.
     */
    void attachOn(E controller);

    /**
     * show a message for the user.
     * 
     * @param message
     *            to show.
     * @param messageType
     *            (messageTitle, AlertType used in javafxApplication) is used to
     *            show different message.
     */
    void showMessage(String message, MessageType messageType);
    /**
     * what to do when a view is showed.
     */
    void show();
}

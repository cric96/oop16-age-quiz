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

}

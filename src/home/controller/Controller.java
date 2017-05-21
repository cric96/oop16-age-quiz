package home.controller;
import java.util.Set;

import home.view.View;
/**
 * A generic controller where you can attach all type of views.
*/
public interface Controller {
    /**
     * get all views attach on a controller.
     * @return
     *  the views.
     */
    Set<? extends View<?>> getViews();
    /**
     * when the controller is switched check if the model
     * is changed and notify to his own views.
     */
    void checkUpdate();
}

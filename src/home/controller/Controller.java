package home.controller;
import home.view.View;
import java.util.Set;
/**
 * A generic controller where you can attach all type of views.
*/
public interface Controller {
    /**
     * get all views attach on a controller.
     * @return
     *  the views.
     */
    Set<View<?>> getViews();
    /**
     * when the controller is switched check if the model
     * is changed and notify to his own views.
     */
    void checkUpdate();
}

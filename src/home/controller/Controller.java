package home.controller;
import java.util.Set;

import home.view.View;
/**
 * A generic controller on which you can attach all type of views.
*/
public interface Controller {
    /**
     * get all views attach on a controller.
     * @return
     *  the views.
     */
    Set<? extends View<?>> getViews();
    /** 
     * check if the model is changed and notify the changes to the views.
     */
    void checkUpdate();
}

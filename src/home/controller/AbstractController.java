package home.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import home.view.View;
/**
 * a skeleton of a controller with associated with a specific view.
 * @param <V>
 *      the type of view
 */
public abstract class AbstractController <V extends View<?>> implements Controller {
    private final Set<V> views;
    /**
     * 
     * @param views
     *  the views that you want to attach on this controller 
     */
    public AbstractController(final V ... views) {
        this.views = new HashSet<>(Arrays.asList(views));
        attachViews();
    }
    /**
     * 
     * @return
     *  the view of this controller
     */
    protected Set<V> getInternalView() {
        return this.views;
    }
    /**
     * how a controller attach his views.
     */
    protected abstract void attachViews();
}

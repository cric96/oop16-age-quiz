package home.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import home.view.MessageType;
import home.view.View;
import home.view.menu.MenuView;
import home.view.quiz.QuizView;
import home.view.world.WorldView;
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
    @SafeVarargs
    public AbstractController(final V ... views) {
        this.views = new HashSet<>(Arrays.asList(views));
        attachViews();
    }
    @Override
    public Set<View<?>> getViews() {
        return this.getInternalView().stream().map(x -> (View<?>) x)
                                  .collect(Collectors.toSet());
    }
    @Override
    public void checkUpdate() {
        this.views.forEach(x -> x.show());
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
    /**
     * tells to each view that some thing is happened.
     * @param message
     *  the message to send
     */
    protected void showErrors(final String message) {
        this.getInternalView().forEach(x -> x.showMessage(message, MessageType.ERROR));
    }
}

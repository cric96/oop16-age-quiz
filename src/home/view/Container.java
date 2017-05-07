package home.view;

import home.controller.Controller;
import home.utility.Pair;

/**
 * the container of the different controller with its view.
 *
 */
public interface Container {
    /**
     * @return
     *  a specific container
     */
    static Container getContainer() {
        return FXContainer.getContainer();
    }
    /**
     * change the current display.
     * @param type
     *  the type of view that you want tho show
     */
    void changeDisplay(ViewType type);
    /**
     * add a controller to this container.
     * @param controller
     *  the controller with is type of view associated
     */
    void addController(Pair<ViewType, Controller> controller);
}

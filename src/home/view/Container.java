package home.view;

import home.view.fx.FXContainer;

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
}

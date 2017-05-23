package home.view.fx.parent;

import home.controller.observer.MenuObserver;
import home.controller.observer.WorldObserver;

/**
 * static factory for parent.
 *
 */
public final class ParentFactory {
    private ParentFactory() { }

    /**
     * create a new parentMenu.
     * @param controller the controller of this parent
     * @return the created parent
     */
    public static CustomParent createMenuParent(final MenuObserver controller) {
        return new ParentMenuImpl(controller);
    }
    /**
     * create new World parent.
     * @param controller the controller of this parent
     * @return the created parent
     */
    public static CustomParent createWorldParent(final WorldObserver controller) {
        return new ParentWorldImpl(controller);
    }
}

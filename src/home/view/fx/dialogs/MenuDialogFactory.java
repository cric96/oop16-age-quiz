package home.view.fx.dialogs;

import java.util.List;

import home.controller.observer.MenuObserver;
import home.controller.profile.Profile;
import javafx.stage.Window;

/**
 * static factory used to create load/new game dialogs.
 */
public final class MenuDialogFactory {
    private MenuDialogFactory() { }

    /**
     * create a new game dialog in menu.
     * 
     * @param profiles
     *            set of possible profile.
     * @param window
     *            owner of this dialog
     * @param controller
     *            controller of menu
     * @return the dialog
     */
    public static MenuDialog createDialogNewGame(final List<Profile> profiles, final Window window,
            final MenuObserver controller) {
        return new MenuDialogLoadGame(profiles, window, controller);
    }

    /**
     * create a new load game dialog in menu.
     * 
     * @param profiles
     *            set of possible profile.
     * @param window
     *            owner of this dialog
     * @param controller
     *            controller of menu
     * @return the dialog
     */
    public static MenuDialog createDialogLoadGame(final List<Profile> profiles, final Window window,
            final MenuObserver controller) {
        return new MenuDialogLoadGame(profiles, window, controller);
    }
}

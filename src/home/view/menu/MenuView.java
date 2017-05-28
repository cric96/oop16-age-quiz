package home.view.menu;

import java.util.List;

import home.controller.observer.MenuObserver;
import home.controller.profile.Profile;
import home.view.View;

/**
 * Specification of View used to implement a generic Menu.
 */
public interface MenuView extends View<MenuObserver> {
    /**
     * method called to show the dialog to select a saved profile.
     * @param profiles the set of saved profile.
     */
    void showSavedGames(List<Profile> profiles);

    /**
     * method called to show the dialog to create a new saving profile.
     * @param profiles the set of saved and free profile.
     */
    void showNewGame(List<Profile> profiles);
}

package home.view.menu;

import java.util.Set;

import home.controller.menu.MenuController;
import home.controller.menu.Profile;
import home.view.View;

/**
 * Specification of View used to implement a generic Menu.
 */
public interface MenuView extends View<MenuController> {
    /**
     * method called to show the dialog to select a saved profile.
     * @param profiles the set of saved profile.
     */
    void showSavedGames(Set<Profile> profiles);

    /**
     * method called to show the dialog to create a new saving profile.
     * @param profiles the set of saved and free profile.
     */
    void showNewGame(Set<Profile> profiles);
}

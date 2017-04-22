package home.view.menu;

import javafx.scene.Scene;

import java.util.Optional;
import java.util.Set;

import home.controller.menu.MenuController;
import home.controller.menu.Profile;
import home.view.FXViewImpl;

/**
 * Implementation of Menu view in javafx.
 */
public class MenuViewImpl extends FXViewImpl implements MenuView{
    private Optional<MenuController> controller;
    private Optional<Scene> scene;

    public MenuViewImpl() {
        this.controller = Optional.empty();
        this.scene = Optional.empty();
    }

    @Override
    public void attachOn(final MenuController controller) {
        this.controller = Optional.of(controller);
        this.scene = Optional.of(new Scene(new GameMenu(controller)));
    }

    @Override
    public void showSavedGames(final Set<Profile> profiles) {
        // TODO Auto-generated method stub
    }

    @Override
    public void showNewGame(final Set<Profile> profiles) {
        // TODO Auto-generated method stub

    }

}

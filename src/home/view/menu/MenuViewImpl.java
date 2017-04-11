package home.view.menu;

import java.io.IOException;
import java.util.Set;

import home.controller.MenuController;
import home.controller.Profile;
import home.view.MessageType;
import javafx.scene.Scene;

/**
 * Implementation of Menu view in javafx.
 */
public class MenuViewImpl extends Scene  implements MenuView {
    private MenuController controller;

    public MenuViewImpl() throws IOException {
        super(new GameMenu());
    }

    @Override
    public void attachOn(final MenuController controller) {
        this.controller = controller;
    }

    @Override
    public void showMessage(final String message, final MessageType type) {
        // TODO Auto-generated method stub

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

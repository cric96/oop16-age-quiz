package home.view.menu.fx;

import javafx.scene.control.ButtonType;
import java.util.Optional;
import java.util.Set;
import home.controller.MenuController;
import home.controller.profile.Profile;
import home.view.MessageType;
import home.view.fx.AbstractFXView;
import home.view.menu.Buttons;
import home.view.menu.MenuView;

/**
 * Implementation of Menu view in javafx.
 */
public class FXMenuViewImpl extends AbstractFXView<ParentMenu> implements MenuView {
    private Optional<MenuController> controller;

    /**
     * create new MenuImpl.
     */
    public FXMenuViewImpl() {
        super();
        this.controller = Optional.empty();
    }

    @Override
    public void attachOn(final MenuController controller) {
        this.controller = Optional.of(controller);
        this.setParent(new ParentMenu(controller));
    }

    @Override
    public void show() {
        super.show();
        this.getParent().addFocus();
    }

    @Override
    public void showSavedGames(final Set<Profile> profiles) {
        final MenuDialog dialog = new MenuDialog();
        this.getParent().removeFocus();
        dialog.show(profiles, controller, Buttons.LOAD_GAME, this.getParent().getScene().getWindow());
        this.getParent().addFocus();
    }

    @Override
    public void showNewGame(final Set<Profile> profiles) {
        final MenuDialog dialog = new MenuDialog();
        this.getParent().removeFocus();
        dialog.show(profiles, controller, Buttons.NEW_GAME, this.getParent().getScene().getWindow());
        this.getParent().addFocus();
    }

    @Override
    public void showMessage(final String message, final MessageType messageType) {
        this.getParent().removeFocus();
        super.showMessage(message, messageType);
        this.getParent().addFocus();
    }

    @Override
    protected void onClickMessage(final MessageType type, final Optional<ButtonType> button) {
        if (type.equals(MessageType.EXIT) && button.equals(Optional.of(ButtonType.OK))) {
            this.controller.get().exitConfirmed();
        }
    }
}

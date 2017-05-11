package home.view.menu.fx;

import javafx.scene.control.ButtonType;
import java.util.Optional;
import java.util.Set;
import home.controller.MenuController;
import home.controller.profile.Profile;
import home.view.MessageType;
import home.view.fx.AbstractFXView;
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
        this.getParent().removeFocus();
        final MenuDialogLoadGame dialog = new MenuDialogLoadGame(profiles, this.getParent().getScene().getWindow(), controller.get());
        dialog.show();
        this.getParent().addFocus();
    }

    @Override
    public void showNewGame(final Set<Profile> profiles) {
        this.getParent().removeFocus();
        final MenuDialogNewGame dialog = new MenuDialogNewGame(profiles, this.getParent().getScene().getWindow(), controller.get());
        dialog.show();
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

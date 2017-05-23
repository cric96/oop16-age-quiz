package home.view.factory;

import java.util.List;
import java.util.Optional;

import home.controller.observer.MenuObserver;
import home.controller.profile.Profile;
import home.view.MessageType;
import home.view.fx.dialog.MenuDialog;
import home.view.fx.dialog.MenuDialogFactory;
import home.view.fx.parent.CustomParent;
import home.view.fx.parent.ParentFactory;
import home.view.fx.parent.ParentMenu;
import home.view.menu.MenuView;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;

/**
 * Implementation of Menu view in javafx.
 */
class MenuViewImpl extends AbstractFXView<Parent> implements MenuView {
    private Optional<MenuObserver> controller;

    /**
     * create new MenuImpl.
     */
    MenuViewImpl() {
        super();
        this.controller = Optional.empty();
    }

    @Override
    public void attachOn(final MenuObserver controller) {
        this.controller = Optional.of(controller);
        this.setParent((Parent) ParentFactory.createMenuParent(controller));
    }

    @Override
    public void show() {
        ((CustomParent) this.getParent()).addFocus();
        ((ParentMenu) this.getParent()).repaint();
    }

    @Override
    public void showSavedGames(final List<Profile> profiles) {
        ((CustomParent) this.getParent()).removeFocus();
        final MenuDialog dialog = MenuDialogFactory.createDialogLoadGame(profiles, this.getParent().getScene().getWindow(), controller.get());
        dialog.show();
        ((CustomParent) this.getParent()).addFocus();
    }

    @Override
    public void showNewGame(final List<Profile> profiles) {
        ((CustomParent) this.getParent()).removeFocus();
        final MenuDialog dialog = MenuDialogFactory.createDialogNewGame(profiles, this.getParent().getScene().getWindow(), controller.get());
        dialog.show();
        ((CustomParent) this.getParent()).addFocus();
    }

    @Override
    public void showMessage(final String message, final MessageType messageType) {
        ((CustomParent) this.getParent()).removeFocus();
        super.showMessage(message, messageType);
        ((CustomParent) this.getParent()).addFocus();
    }

    @Override
    protected void onClickMessage(final MessageType type, final Optional<ButtonType> button) {
        if (type.equals(MessageType.EXIT) && button.equals(Optional.of(ButtonType.OK))) {
            this.controller.get().exitConfirmed();
        }
    }
}

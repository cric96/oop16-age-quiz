package home.view.menu.fx;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class FXMenuViewImpl extends AbstractFXView implements MenuView {
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
        final ParentMenu pm = new ParentMenu(controller);
        final Scene scene = new Scene(pm);
        this.setScene(scene);
    }

    @Override
    public void showSavedGames(final Set<Profile> profiles) {
        final MenuDialog dialog = new MenuDialog();
        dialog.show(profiles, controller, Buttons.LOAD_GAME, Optional.of(this));
    }

    @Override
    public void showNewGame(final Set<Profile> profiles) {
        final MenuDialog dialog = new MenuDialog();
        dialog.show(profiles, controller, Buttons.NEW_GAME, Optional.of(this));
    }

    /*
     * Qui segue la reimplementazione di show message perchè se utilizzassi il metodo di default non saprei come chiamare il metodo
     * controller.get().exitConfirmed();
     * Idee? 
     */

    @Override
    public void showMessage(final String message, final MessageType messageType) {
        final Alert alert = new Alert(messageType.getAlertType());
        alert.initOwner(this.getScene().getWindow());
        alert.setTitle(messageType.getMessageTitle());
        alert.setHeaderText(message);
        final Optional<ButtonType> result = alert.showAndWait();
        if (messageType.equals(MessageType.EXIT) && result.equals(Optional.of(ButtonType.OK))) {
            this.controller.get().exitConfirmed();
        }
    }

    //RICHI GUARDA SE TI PUò FARE COMODO! VIENE CHIAMATA OGNI VOLTA CHE LA VIEW DEVE ESSERE MOSTRATA
    @Override
    public void show() {
        // TODO Auto-generated method stub
    }
}

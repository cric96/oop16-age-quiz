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
import javafx.stage.Stage;
/**
 * Implementation of Menu view in javafx.
 */
public class FXMenuViewImpl extends AbstractFXView implements MenuView {
    private Optional<MenuController> controller;
    /**
     * create new MenuImpl.
     * @param principalStage stage 
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
    public void showSavedGames(final Set<Profile> profiles) {
        final MenuDialog dialog = new MenuDialog();
        dialog.show(profiles, controller, Buttons.LOAD_GAME, this.getParent().getScene().getWindow());
    }

    @Override
    public void showNewGame(final Set<Profile> profiles) {
        final MenuDialog dialog = new MenuDialog();
        dialog.show(profiles, controller, Buttons.NEW_GAME, this.getParent().getScene().getWindow());
    }

    //RICHI GUARDA SE TI PUò FARE COMODO! VIENE CHIAMATA OGNI VOLTA CHE LA VIEW DEVE ESSERE MOSTRATA
    @Override
    public void show() {
        // TODO Auto-generated method stub
    }

    @Override
    protected void onClickMessage(final MessageType type, final Optional<ButtonType> button) {
        if (type.equals(MessageType.EXIT) && button.equals(Optional.of(ButtonType.OK))) {
            this.controller.get().exitConfirmed();
        }
    }
}

package home.view.fx.dialog;

import java.util.List;
import java.util.Optional;

import home.controller.observer.MenuObserver;
import home.controller.profile.Profile;
import home.utility.view.FontManager;
import home.view.fx.button.MenuButtonFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Window;

/**
 * specialization of AbstractMenuDialog to create a load dialog.
 */
class MenuDialogLoadGame extends AbstractMenuDialog {
    private final Label date = new Label();
    /**
     * @param profiles 
     *          profiles that the dialog should display
     * @param window
     *          owner of this dialog
     * @param controller 
     *          observer of menu
     */
    MenuDialogLoadGame(final List<Profile> profiles, final Window window, final MenuObserver controller) {
        super(window, profiles, controller);
        this.date.setFont(FontManager.getGeneralFont());
        this.getAlert().setTitle(this.getButtonText().getString("LOAD_GAME"));
    }

    @Override
    public void show() {
        super.show();
        final Optional<ButtonType> res = super.getAlert().showAndWait();
        if (res.isPresent() && res.get().getText().equals(this.getButtonText().getString("LOAD"))) {
            this.getController().loadGame(this.getSelectedProfile().get());
        }
    }

    @Override
    protected void setDeleteMessage() { }

    @Override
    protected void initNode() {
        super.getRoot().getChildren().add(this.date);
    }

    @Override
    protected void initButtonContainer() {
        super.getButtonContainer().getChildren().addAll(this.getButtonList());
    }

    @Override
    protected void onClickButton(final Profile profile) {
        this.date.setText(profile.getSaveDate());
        this.setSelectedProfile(profile);
        final ButtonType btype = new ButtonType(this.getButtonText().getString("LOAD"));
        this.getAlert().getButtonTypes().setAll(btype);
    }

    @Override
    protected Button createButton(final Profile profile) {
        return MenuButtonFactory.createProfileButtonLoadGame(profile);
    }
}

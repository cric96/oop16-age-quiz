package home.view.fx.dialog;

import java.util.List;
import java.util.Optional;

import home.controller.observer.MenuObserver;
import home.controller.profile.Profile;
import home.utility.view.FontManager;
import home.view.fx.button.MenuButtonFactory;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * specialization of menu dialog to create a new game profile.
 */
class MenuDialogNewGame extends AbstractMenuDialog {
    private static final int MAX_CHAR = 10;
    private final TextField profileName = new TextField();
    private final Label messageInfo = new Label(this.getLabelText().getString("WARNING"));
    /**
     * @param profiles 
     *          profiles that the dialog should display
     * @param win 
     *          owner of this dialog
     * @param controller 
     *          observer of menu
     */
    MenuDialogNewGame(final List<Profile> profiles, final Window win, final MenuObserver controller) {
        super(win, profiles, controller);
        //TO LIMIT THE MAX NUMER OF ELEMENT 
        this.profileName.lengthProperty().addListener((ob, oldV, newV) -> {
            if (newV.intValue() > oldV.intValue() && newV.intValue() > MAX_CHAR) {
                this.profileName.setText(this.profileName.getText().substring(0, MAX_CHAR));
            }
        });
        this.messageInfo.setFont(FontManager.getGeneralFont());
        this.messageInfo.setVisible(false);
        this.profileName.setVisible(false);
        this.getAlert().setTitle(this.getButtonText().getString("NEW_GAME"));
    }

    @Override
    protected void initNode() {
        this.getRoot().getChildren().add(this.profileName);
    }

    @Override
    protected void setDeleteMessage() {
        this.getRoot().getChildren().add(this.messageInfo);
    }

    @Override
    protected void initButtonContainer() {
        this.getButtonContainer().getChildren().addAll(this.getButtonList());
    }

    @Override
    public void show() {
        super.show();
        final Optional<ButtonType> res = this.getAlert().showAndWait();
        if (res.isPresent() && res.get().getText().equals(this.getButtonText().getString("CREATE"))) {
            this.getController().createGame(this.profileName.getText(), this.getSelectedProfile().get());
        }
    }


    @Override
    protected void onClickButton(final Profile profile) {
        if (profile.isEnabled()) {
            messageInfo.setVisible(true);
        } else {
            messageInfo.setVisible(false);
        }
        Platform.runLater(() -> this.profileName.requestFocus());
        this.profileName.setVisible(true);
        this.setSelectedProfile(profile);
        this.profileName.setText("");
        this.getAlert().getButtonTypes().setAll(new ButtonType(this.getButtonText().getString("CREATE")));
    }

    @Override
    protected Button createButton(final Profile profile) {
        return MenuButtonFactory.createProfileButtonNewGame(profile);
    }
}

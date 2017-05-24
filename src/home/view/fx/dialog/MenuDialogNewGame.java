package home.view.fx.dialog;

import java.util.List;
import java.util.Optional;

import home.controller.observer.MenuObserver;
import home.controller.profile.Profile;
import home.utility.view.FontManager;
import home.view.fx.button.ButtonProfile;
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
    protected void setProfileButton(final List<Profile> profiles) {
        profiles.forEach(profile -> {
            final Button button = MenuButtonFactory.createProfileButtonNewGame(profile);
            button.setPrefWidth(super.getButtonWidth());
            this.getButtonList().add(button);
            button.setOnMouseClicked(click -> {
                ((ButtonProfile) button).select();
                this.getButtonList().stream().filter(b -> !b.equals(button)).forEach(x -> ((ButtonProfile) x).deselect());
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
            });
        });
    }
}
package home.view.menu.fx;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import home.controller.observer.MenuObserver;
import home.controller.profile.Profile;
import home.utility.Utility;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * specialization of menu dialog to create a new game profile.
 */
public class MenuDialogNewGame extends AbstractMenuDialog {
    private final TextField profileName = new TextField();
    private final Label messageInfo = new Label(this.getLabelText().getString("WARNING"));
    private final Set<ProfileButton> buttonSet = new HashSet<>();
    private final MenuObserver controller;

    /**
     * 
     * @param profiles 
     * @param win 
     * @param controller 
     */
    public MenuDialogNewGame(final Set<Profile> profiles, final Window win, final MenuObserver controller) {
        super(win);
        this.controller = controller;
        this.messageInfo.setFont(Utility.getGeneralFont());
        this.messageInfo.setVisible(false);
        this.profileName.setVisible(false);
        profiles.forEach(profile -> {
            final ProfileButton button = new ButtonProfileNewGame(profile);
            button.setPrefWidth(super.getButtonWidth());
            buttonSet.add(button);
            button.setOnMouseClicked(click -> {
                if (profile.isEnabled()) {
                    messageInfo.setVisible(true);
                } else {
                    messageInfo.setVisible(false);
                }
                this.profileName.setVisible(true);
                this.setSelectedProfile(profile);
                this.profileName.setText("");
                this.getAlert().getButtonTypes().setAll(new ButtonType(this.getButtonText().getString("CREATE")));
            });
        });
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
        this.getButtonContainer().getChildren().addAll(this.buttonSet);
    }

    @Override
    public void show() {
        super.show();
        final Optional<ButtonType> res = this.getAlert().showAndWait();
        if (res.isPresent() && res.get().getText().equals(this.getButtonText().getString("CREATE"))) {
            controller.createGame(this.profileName.getText(), this.getSelectedProfile().get());
        }
    }
}

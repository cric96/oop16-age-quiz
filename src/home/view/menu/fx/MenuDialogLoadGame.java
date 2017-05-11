package home.view.menu.fx;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import home.controller.MenuController;
import home.controller.profile.Profile;
import home.utility.Utility;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Window;

/**
 * specialization of AbstractMenuDialog to create a load dialog.
 */
public class MenuDialogLoadGame extends AbstractMenuDialog {
    private final Set<ProfileButton> buttonSet = new HashSet<>();
    private final MenuController controller;
    private final Label date = new Label();
    /**
     * @param profiles 
     * @param window 
     * @param controller 
     */
    public MenuDialogLoadGame(final Set<Profile> profiles, final Window window, final MenuController controller) {
        super(window);
        this.date.setFont(Utility.getGeneralFont());
        this.controller = controller;
        profiles.forEach(profile -> {
            final ProfileButton button = new ButtonProfileLoadGame(profile);
            button.setPrefWidth(super.getButtonWidth());
            buttonSet.add(button);
            button.setOnMouseClicked(click -> {
                this.date.setText(profile.getSaveDate());
                this.setSelectedProfile(profile);
                super.getAlert().getButtonTypes().setAll(new ButtonType(this.getButtonText().getString("LOAD")));
            });
        });
        this.getAlert().setTitle(this.getButtonText().getString("LOAD_GAME"));
    }

    @Override
    public void show() {
        super.show();
        final Optional<ButtonType> res = super.getAlert().showAndWait();
        if (res.isPresent() && res.get().getText().equals(this.getButtonText().getString("LOAD"))) {
            controller.loadGame(this.getSelectedProfile().get());
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
        super.getButtonContainer().getChildren().addAll(this.buttonSet);
    }

}

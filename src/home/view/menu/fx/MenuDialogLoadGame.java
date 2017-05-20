package home.view.menu.fx;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import home.controller.observer.MenuObserver;
import home.controller.profile.Profile;
import home.utility.view.FontManager;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Window;

/**
 * specialization of AbstractMenuDialog to create a load dialog.
 */
public class MenuDialogLoadGame extends AbstractMenuDialog {
    private final List<ProfileButton> buttonSet = new ArrayList<>();
    private final MenuObserver controller;
    private final Label date = new Label();
    /**
     * @param profiles 
     * @param window 
     * @param controller 
     */
    public MenuDialogLoadGame(final List<Profile> profiles, final Window window, final MenuObserver controller) {
        super(window);
        this.date.setFont(FontManager.getGeneralFont());
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

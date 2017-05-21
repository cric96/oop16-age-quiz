package home.view.menu.fx;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import home.controller.observer.MenuObserver;
import home.controller.profile.Profile;
import home.utility.BundleLanguageManager;
import home.utility.Bundles;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
* a class to create a show/load dialog in javafx.
*/
public abstract class AbstractMenuDialog implements MenuDialog {
    private final List<ProfileButton> buttonSet = new ArrayList<>();
    private final MenuObserver controller;
    private Optional<Profile> selectedProfile;
    private final Alert dialog = new Alert(AlertType.NONE);
    private final HBox buttonContainer; 
    private final VBox root;
    private static final int BUTTON_WIDTH = 200;
    private  ResourceBundle buttonText = BundleLanguageManager.get().getBundle(Bundles.BUTTON);
    private  ResourceBundle labelText = BundleLanguageManager.get().getBundle(Bundles.LABEL);


    /**
     * @param win 
     *          the window owner of dialog
     * @param profiles
     *          possible saving slots 
     * @param controller 
     *          observer of menu view
     */
    public AbstractMenuDialog(final Window win, final List<Profile> profiles, final MenuObserver controller) {
        this.controller = controller;
        final int boxPadding = 20;
        final int yLayoutBox = 10;
        buttonContainer = new HBox(boxPadding);
        buttonContainer.layoutYProperty().set(yLayoutBox);
        root = new VBox(boxPadding);
        root.getChildren().add(buttonContainer);
        dialog.getButtonTypes().setAll(new ButtonType(buttonText.getString("CANCEL")));
        dialog.getDialogPane().setContent(root);
        dialog.initOwner(win);
        dialog.setResizable(false);
        dialog.initStyle(StageStyle.DECORATED);
        dialog.initModality(Modality.APPLICATION_MODAL);
        this.setProfileButton(profiles);
    }

    @Override
    public void show() {
       initButtonContainer();
       initNode();
       setDeleteMessage();
       this.buttonText = BundleLanguageManager.get().getBundle(Bundles.BUTTON);
       this.labelText = BundleLanguageManager.get().getBundle(Bundles.LABEL);
    }

    /**
     * 
     * @return the container of button
     */
    protected HBox getButtonContainer() {
        return this.buttonContainer;
    }

    /**
     * 
     * @return the dialog
     */
    protected Alert getAlert() {
        return this.dialog;
    }

    /**
     * 
     * @return the root container of dialog
     */
    protected VBox getRoot() {
        return this.root;
    }

    /**
     * @return the buttonWidth
     */
    public static int getButtonWidth() {
        return BUTTON_WIDTH;
    }

    /**
     * @return the buttonText
     */
    public ResourceBundle getButtonText() {
        return buttonText;
    }

    /**
     * @return the labelText
     */
    public ResourceBundle getLabelText() {
        return labelText;
    }

    /**
     * insert buttons that represent profiles in the allert.
     * @param profiles 
     *          possible usable profiles
     */
    protected abstract void setProfileButton(List<Profile> profiles);

    /**
     * initialize the node under the profiles button.
     */
    protected abstract void initNode();
    /**
     * used by NewGameDialog to insert a allert message when user override a profile.
     */
    protected abstract void setDeleteMessage();

    /**
     * initialize the container of button.
     */
    protected abstract void initButtonContainer();

    /**
     * 
     * @return selected profile
     */
    protected Optional<Profile> getSelectedProfile() {
        return selectedProfile;
    }

    /**
     * 
     * @param selectedProfile 
     */
    protected void setSelectedProfile(final Profile selectedProfile) {
        this.selectedProfile = Optional.ofNullable(selectedProfile);
    }

    /**
     * 
     * @return
     *      the list of button to insert in the dialog.
     */
    protected List<ProfileButton> getButtonList() {
        return this.buttonSet;
    }

    /**
     * @return controller 
     *      the controller of menu view.
     */
    protected MenuObserver getController() {
        return this.controller;
    }
}

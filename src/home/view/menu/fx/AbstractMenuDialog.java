package home.view.menu.fx;

import java.util.Optional;
import java.util.ResourceBundle;

import home.controller.profile.Profile;
import home.utility.BundleLanguageManager;
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
    private Optional<Profile> selectedProfile;
    private final Alert dialog = new Alert(AlertType.NONE);
    private final HBox buttonContainer; 
    private final VBox root;
    private static final int BUTTON_WIDTH = 200;
    private final ResourceBundle buttonText = BundleLanguageManager.get().getBundle("ButtonBundle");
    private final ResourceBundle labelText = BundleLanguageManager.get().getBundle("LabelBundle");


    /**
     * @param win 
     */
    public AbstractMenuDialog(final Window win) {
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
    }

    @Override
    public void show() {
       initButtonContainer();
       initNode();
       setDeleteMessage();
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
     * 
     */

    protected abstract void initNode();
    /**
     * 
     */
    protected abstract void setDeleteMessage();

    /**
     * 
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
}
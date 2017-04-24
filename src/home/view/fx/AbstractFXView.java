package home.view.fx;

import java.util.Optional;

import home.view.MessageType;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


/**
 * a skeleton of a FXView.
 */
public abstract class AbstractFXView implements FXView {
    private Optional<Scene> scene;

    /**
     * create a abstractFxView with a Optiona.empty scene.
     */
    public AbstractFXView() {
        this.scene = Optional.empty();
    }

    @Override
    public Scene getScene() {
        return scene.get();
    }

    /**
     * to set the scene of this view.
     * @param scene fxscene.
     */
    protected void setScene(final Scene scene) {
        this.scene = Optional.ofNullable(scene);
    }

    /**
     * show a message dialog for the user.
     * @param message to show.
     * @param messageType type of Aler dialog.
     */
    public void showMessage(final String message, final MessageType messageType) {
        final Alert alert = new Alert(messageType.getAlertType());
        alert.initOwner(this.getScene().getWindow());
        alert.setTitle(messageType.getMessageTitle());
        alert.setHeaderText(message);
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK && messageType.equals(MessageType.EXIT)) {
            System.exit(0);
        }
    }
}

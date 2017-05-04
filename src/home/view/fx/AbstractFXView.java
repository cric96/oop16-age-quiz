package home.view.fx;

import java.util.Optional;

import home.view.MessageType;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * a skeleton of a FXView.
 */
public abstract class AbstractFXView implements FXView {
    private Optional<Parent> scene;

    /**
     * create a abstractFxView with a Optiona.empty scene.
     */
    public AbstractFXView() {
        this.scene = Optional.empty();
    }

    @Override
    public Parent getParent() {
        return scene.get();
    }

    /**
     * to set the scene of this view.
     * @param parent fxparent.
     */
    protected void setParent(final Parent parent) {
        this.scene = Optional.ofNullable(parent);
    }

    //TEMPLATE-METHOD
    /**
     * show a message dialog for the user.
     * @param message to show.
     * @param messageType type of Aler dialog.
     */
    public void showMessage(final String message, final MessageType messageType) {
        final Alert alert = new Alert(messageType.getAlertType());
        alert.initOwner(this.getParent().getScene().getWindow());
        alert.setTitle(messageType.getMessageTitle());
        alert.setHeaderText(message);
        this.onClickMessage(messageType, alert.showAndWait());
    }
    /**
     * what to do when the user click on alert.
     * @param type
     *  the type of message
     * @param button
     *  the button clicked
     */
    protected abstract void onClickMessage(MessageType type, Optional<ButtonType> button);
}

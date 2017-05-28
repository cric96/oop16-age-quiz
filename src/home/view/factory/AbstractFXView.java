package home.view.factory;

import java.util.Optional;

import home.view.MessageType;
import home.view.fx.FXView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * a skeleton of world fx view.
 * @param <P> 
 *         for example WorldParent or MenuParent.
 */
abstract class AbstractFXView<P extends Parent> implements FXView {
    private Optional<P> scene;

    /**
     * create a abstractFxView with a Optiona.empty scene.
     */
   AbstractFXView() {
        this.scene = Optional.empty();
    }

    @Override
    public P getParent() {
        return scene.get();
    }

    /**
     * to set the scene of this view.
     * @param parent fxparent.
     */
    protected void setParent(final P parent) {
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
        alert.initOwner(((Node) this.getParent()).getScene().getWindow());
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

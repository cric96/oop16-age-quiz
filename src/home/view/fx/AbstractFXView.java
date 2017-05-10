package home.view.fx;

import java.util.Optional;

import home.view.MessageType;
import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;

/**
 * a skeleton of world fx view.
 * @param <P> 
 */
public abstract class AbstractFXView<P extends Parent> implements FXView {
    private Optional<P> scene;

    /**
     * create a abstractFxView with a Optiona.empty scene.
     */
    public AbstractFXView() {
        this.scene = Optional.empty();
    }

    @Override
    public P getParent() {
        return scene.get();
    }

    /**
     * 
     */
    public void show() {
        final double duration = 0.4;
        final FadeTransition ft = new FadeTransition(Duration.seconds(duration), this.getParent());
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
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

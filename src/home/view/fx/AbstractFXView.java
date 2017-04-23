package home.view.fx;

import java.util.Optional;

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
     * display specific dialog with a message for the user. 
     * @param message to show.
     * @param type is used to show different window message.
     * @return 
     */
    public static void showMessage(final String message, final FXMessageType type) {
        final Alert alert = new Alert(type.getAlertType());
        FXContainer.getContainer().showDialog(alert);
        alert.setTitle(type.getMessageTitle());
        alert.setHeaderText(message);
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK && type.equals(FXMessageType.EXIT)) {
            System.exit(0);
        }
    }

}

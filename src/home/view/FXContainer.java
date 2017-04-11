package home.view;
import java.util.Optional;

import home.view.menu.GameMenu;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *  View container realized in javafx.
 */
class FXContainer implements Container {
    private static final FXContainer SINGLETON = new FXContainer();
    private Optional<Stage> stage;

    private FXContainer() {
        this.stage = Optional.empty();
    }

    public void setStage(final Stage stage) {
        this.stage = Optional.of(stage);
    }

    static FXContainer getContainer() {
        return FXContainer.SINGLETON;
    }
}

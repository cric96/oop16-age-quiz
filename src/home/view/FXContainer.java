package home.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import home.controller.Controller;
import home.utility.Pair;
import home.utility.Utility;
import home.view.fx.FXView;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 * implementation ad-hoc for a FXView Container.
 */
//package-protected
final class FXContainer implements Container {
    private static final FXContainer SINGLETON = new FXContainer();
    private Optional<Stage> stage;
    private Scene principalScene;
    private boolean firstView = true;
    private final Map<ViewType, Controller> controllers;

    private FXContainer() {
        this.stage = Optional.empty();
        this.controllers = new HashMap<>();
    }

    /**
     * set the stage of application to FXContainer.
     * @param stage javafx.
     */
    public void setStage(final Stage stage) {
        this.stage = Optional.of(stage);
        if (!this.stage.get().equals(Optional.empty())) {
            this.stage.get().setFullScreen(true);
            this.stage.get().setResizable(false);
            //this.stage.get().setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            this.stage.get().setTitle(Utility.getTitle());
        }
    }

    /**
     * 
     * @return the instance of FXContainer.
     */
    public static FXContainer getContainer() {
        return FXContainer.SINGLETON;
    }

    /**
     * add a (Controller -> View) to FXContainer.
     * @param controller controller of View.
     */
    public void addController(final Pair<ViewType, Controller> controller) {
        this.controllers.put(controller.getX(), controller.getY());
    }

    @Override
    public void changeDisplay(final ViewType type) {
        final Controller changeController = Optional.ofNullable(this.controllers.get(type)).orElseThrow(() -> new IllegalArgumentException());
        changeController.checkUpdate();
        changeController.getViews().forEach(e -> {
            if (e instanceof FXView) {
                if (firstView) {
                    this.principalScene = new Scene(((FXView) e).getParent());
                    this.stage.get().setScene(principalScene);
                    this.stage.get().setFullScreen(true);
                    this.firstView = false;
                } else {
                    this.stage.get().getScene().setRoot(((FXView) e).getParent());
                }
            }
        });
    }

    /**
     * @return the principalStage of application
     */
    public Stage getPrincipalStage() {
        return this.stage.get();
    }
}

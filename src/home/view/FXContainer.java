package home.view;
import java.util.HashMap;


import java.util.Map;
import java.util.Optional;

import home.controller.Controller;
import home.utility.Pair;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
//package-protected
final class FXContainer implements Container {
    private static final FXContainer SINGLETON = new FXContainer();
    private Optional<Stage> stage;
    private final Map<ViewType, Controller> controllers;

    private FXContainer() {
        this.stage = Optional.empty();
        this.controllers = new HashMap<>();
    }

    public void setStage(final Stage stage) {
        this.stage = Optional.of(stage);
    }

    public static FXContainer getContainer() {
        return FXContainer.SINGLETON;
    }

    public void addController(final Pair<ViewType, Controller> controller) {
        this.controllers.put(controller.getX(), controller.getY());
    }

    @Override
    public void changeDisplay(final ViewType type) {
        final Controller changeController = Optional.ofNullable(this.controllers.get(type)).orElseThrow(() -> new IllegalArgumentException());
        changeController.checkUpdate();
        changeController.getViews().forEach(e -> {
            if (e instanceof FXView) {
                this.stage.get().setScene(((FXView) e).getScene());
            }
        });
    }

    public void showDialog(final Alert createProfileStage) {
        createProfileStage.initOwner(this.stage.get());
    }
}

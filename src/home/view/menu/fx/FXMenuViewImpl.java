package home.view.menu.fx;

import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import home.controller.menu.MenuController;
import home.controller.menu.Profile;
import home.utility.Pair;
import home.view.fx.AbstractFXView;
import home.view.fx.FXMessageType;
import home.view.menu.MenuView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Implementation of Menu view in javafx.
 */
public class FXMenuViewImpl extends AbstractFXView implements MenuView {
    private Optional<MenuController> controller;

    /**
     * create new MenuImpl.
     */
    public FXMenuViewImpl() {
        super();
        this.controller = Optional.empty();
    }

    @Override
    public void attachOn(final MenuController controller) {
        this.controller = Optional.of(controller);
        this.setScene(new Scene(new GameMenu(controller)));
    }

    @Override
    public void showSavedGames(final Set<Profile> profiles) {
        showLoadNew(Mode.LOAD, profiles);
    }

    @Override
    public void showNewGame(final Set<Profile> profiles) {
        showLoadNew(Mode.NEW, profiles);
    }

    private void showLoadNew(final Mode m, final Set<Profile> profiles) {
        final int boxPadding = 10;
        final int buttonWidth = 200;
        final int yLayoutBox = 20;
        final Map<ProfileButton, Pair<TextField, Profile>> map = new HashMap<>();
        final VBox root = new VBox();
        final HBox container = new HBox(boxPadding);

        final Text allertMessage = new Text("Attenzione tutti i salvataggi di questo slot verranno eliminati\nContinuare?");
        final Alert alert = new Alert(AlertType.NONE);
        final ButtonType selectButton = new ButtonType(m.equals(Mode.LOAD) ? "Select" : "Create");
        container.layoutYProperty().set(yLayoutBox);
        allertMessage.setVisible(false);

        alert.setTitle(m.getTitle());
        alert.getDialogPane().setContent(root);

        alert.getButtonTypes().setAll(selectButton);

        profiles.forEach(e -> {
            try {
                final ProfileButton buttonP = new ProfileButton(e);
                if (m.equals(Mode.LOAD) && !e.isEnabled()) {
                    buttonP.setDisable(true);
                }
                buttonP.setPrefWidth(buttonWidth);
                final VBox box = new VBox(20);
                final TextField name = new TextField();
                name.setVisible(false);
                box.getChildren().add(buttonP);
                if (m.equals(Mode.NEW)) {
                    box.getChildren().add(name);
                }
                map.put(buttonP, Pair.createPair(name, e));

                buttonP.setOnMouseClicked(x -> {
                    map.get(buttonP).getX().setVisible(true);
                    map.values()
                       .stream()
                       .filter(y -> !y.equals(map.get(buttonP)))
                       .forEach(a -> {
                           a.getX().setVisible(false); 
                           a.getX().setText("");
                       });
                    if (m.equals(Mode.NEW)) {
                        if (e.getName().isPresent()) {
                            allertMessage.setVisible(true);
                        } else {
                            allertMessage.setVisible(false);
                        }
                    }
                });
                container.getChildren().add(box);
            } catch (IOException x) {
                AbstractFXView.showMessage(x.getMessage(), FXMessageType.ERROR);
            }
        });

        root.getChildren().add(container);
        if (m.equals(Mode.NEW)) {
            root.getChildren().add(allertMessage);
        }

        alert.initOwner(this.getScene().getWindow());

        final Optional<ButtonType> result = alert.showAndWait();
        if ((result.get() == selectButton) && (m.equals(Mode.NEW))) {
            final String s = map.entrySet().stream()
                                           .map(b -> b.getValue())
                                           .filter(t -> !t.getX().getText().equals(""))
                                           .findFirst()
                                           .get().getX().getText();

            this.controller.get().createGame(s, map.values().stream()
                                                            .filter(a -> a.getX().getText().equals(s))
                                                            .findFirst()
                                                            .get().getY());
        }
    }

    private enum Mode {
        NEW("New Game"),

        LOAD("Load Game");

        private String title;

        Mode(final String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }
    }
}

package home.view.menu.fx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import home.controller.MenuController;
import home.controller.profile.Profile;
import home.utility.Pair;
import home.view.MessageType;
import home.view.menu.Buttons;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * a class to create a show/load dialog in javafx.
 */
public class MenuDialog {
    private Optional<Profile> selProfile = Optional.empty();

    /**
     * 
     * @param profiles to show.
     * @param controller 
     * @param mode modality: LOAD_GAME, NEW_GAME
     * @param fxMenuViewImpl owner of this dialog.
     */
    public void show(final Set<Profile> profiles, final Optional<MenuController> controller, final Buttons mode, final FXMenuViewImpl fxMenuViewImpl) {
        if (!mode.equals(Buttons.LOAD_GAME) && !mode.equals(Buttons.NEW_GAME)) {
            throw new IllegalArgumentException();
        }
        final int boxPadding = 10;
        final int buttonWidth = 200;
        final int yLayoutBox = 10;
        final ButtonType createButton = new ButtonType("Create");
        final ButtonType selectButton = new ButtonType("Load");
        final Map<ProfileButton, Pair<Profile, Pair<TextField, Text>>> map = new HashMap<>();
        final Text deleteDataMessage = new Text("Warning! all saved data will be deleted.\nContine?");
        deleteDataMessage.setVisible(false);

        final HBox container = new HBox(boxPadding);
        container.layoutYProperty().set(yLayoutBox);

        final VBox root = new VBox();
        root.getChildren().addAll(container);

        final Alert dialog = new Alert(AlertType.NONE);
        dialog.setTitle(mode.getText());
        dialog.getButtonTypes().setAll(new ButtonType("Cancel"));
        dialog.getDialogPane().setContent(root);
        dialog.initOwner(fxMenuViewImpl.getScene().getWindow());

        if (mode.equals(Buttons.NEW_GAME)) {
            root.getChildren().add(deleteDataMessage); 
        } 
        profiles.forEach(e -> {
            try {
                final VBox box = new VBox(20);
                final ProfileButton buttonP = new ProfileButton(e, mode);
                buttonP.setPrefWidth(buttonWidth);

                final TextField name = new TextField();
                name.setVisible(false);

                final Text date = new Text(e.getSaveDate());
                date.setVisible(false);

                box.getChildren().add(buttonP);

                if (mode.equals(Buttons.LOAD_GAME)) {
                    box.getChildren().add(date);
                    if (!e.isEnabled()) {
                        buttonP.setDisable(true);
                    }
                } else if (mode.equals(Buttons.NEW_GAME)) {
                    box.getChildren().add(name);
                }

                map.put(buttonP, Pair.createPair(e, Pair.createPair(name, date)));

                /*ON MOUSE CLICKED*/
                buttonP.setOnMouseClicked(x -> {
                    selProfile = Optional.of(e);

                    if (mode.equals(Buttons.NEW_GAME)) {
                        dialog.getButtonTypes().setAll(createButton);
                        map.get(buttonP).getY().getX().setVisible(true);
                        map.values()
                           .stream()
                           .filter(y -> !y.equals(map.get(buttonP)))
                           .forEach(a -> {
                               a.getY().getX().setVisible(false);
                               a.getY().getX().setText("");
                           });

                        if (e.getName().isPresent()) {
                            deleteDataMessage.setVisible(true);
                        } else {
                            deleteDataMessage.setVisible(false);
                        }
                    } else if (mode.equals((Buttons.LOAD_GAME))) {
                        dialog.getButtonTypes().setAll(selectButton);
                        map.get(buttonP).getY().getY().setVisible(true);
                        map.values()
                           .stream()
                           .filter(y -> !y.equals(map.get(buttonP)))
                           .forEach(a -> {
                               a.getY().getY().setVisible(false);
                               a.getY().getY().setText(e.getSaveDate());
                           });
                        dialog.getButtonTypes().setAll(selectButton);
                    }
                });
                container.getChildren().add(box);
            } catch (IOException x) {
                fxMenuViewImpl.showMessage(x.getMessage(), MessageType.ERROR);
            }
        });

        final Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.get().equals(createButton)) {
                    controller.get().createGame(map.entrySet().stream()
                                                                   .map(b -> b.getValue())
                                                                   .filter(t -> !t.getY().getX().getText().equals(""))
                                                                   .findFirst()
                                                                   .get().getY().getX().getText(), selProfile.orElseThrow(() -> new IllegalStateException()));
            } else if (result.get() .equals(selectButton)) {
                controller.get().loadGame(selProfile.get());
            }
        }
    }
}

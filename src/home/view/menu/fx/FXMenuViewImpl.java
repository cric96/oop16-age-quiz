package home.view.menu.fx;

import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import home.controller.MenuController;
import home.controller.profile.Profile;
import home.utility.Pair;
import home.view.fx.AbstractFXView;
import home.view.fx.FXMessageType;
import home.view.menu.Buttons;
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
    private Optional<Profile> selProfile;
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
        final ParentMenu pm = new ParentMenu(controller);
        final Scene scene = new Scene(pm);
        this.setScene(scene);
    }

    @Override
    public void showSavedGames(final Set<Profile> profiles) {
        showLoadNew(Buttons.LOAD_GAME, profiles);
    }

    @Override
    public void showNewGame(final Set<Profile> profiles) {
        showLoadNew(Buttons.NEW_GAME, profiles);
    }

    private void showLoadNew(final Buttons m, final Set<Profile> profiles) {
        final int boxPadding = 10;
        final int buttonWidth = 200;
        final int yLayoutBox = 10;
        final Map<ProfileButton, Pair<TextField, Profile>> map = new HashMap<>();
        final VBox root = new VBox();
        final HBox container = new HBox(boxPadding);
        final Text deleteDataMessage = new Text("Warning! all saved data will be deleted.\nContine?");
        final ButtonType selectButton = new ButtonType(m.equals(Buttons.LOAD_GAME) ? "Select" : "Create");
        final Alert dialog = new Alert(AlertType.NONE);

        container.layoutYProperty().set(yLayoutBox);
        deleteDataMessage.setVisible(false);
        dialog.setTitle(m.getText());
        dialog.getDialogPane().setContent(root);
        if (m.equals(Buttons.NEW_GAME)) {
            dialog.getButtonTypes().setAll(selectButton);
        }
        dialog.initOwner(this.getScene().getWindow());

        root.getChildren().add(container);

        profiles.forEach(e -> {
            try {
                final ProfileButton buttonP = new ProfileButton(e);
                this.selProfile = Optional.of(e);
                buttonP.requestFocus();
                if (m.equals(Buttons.LOAD_GAME) && !e.isEnabled()) {
                    buttonP.setDisable(true);
                }
                buttonP.setPrefWidth(buttonWidth);
                final VBox box = new VBox(20);
                final TextField name = new TextField();
                name.setVisible(false);
                box.getChildren().add(buttonP);
                if (m.equals(Buttons.NEW_GAME)) {
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

                    if (m.equals(Buttons.NEW_GAME)) {
                        if (e.getName().isPresent()) {
                            deleteDataMessage.setVisible(true);
                        } else {
                            deleteDataMessage.setVisible(false);
                        }
                    } else if (m.equals((Buttons.LOAD_GAME))) {
                        this.selProfile = Optional.of(e);
                        dialog.getButtonTypes().setAll(selectButton);
                        dialog.setHeight(140);
                    }
                });
                container.getChildren().add(box);
            } catch (IOException x) {
                AbstractFXView.showMessage(x.getMessage(), FXMessageType.ERROR);
            }
        });

        /*
         * se la modalità è New Game aggiungo la Label per avvertire la cancellazione dei dati
         */
        if (m.equals(Buttons.NEW_GAME)) {
            root.getChildren().add(deleteDataMessage); 
        }

        final Optional<ButtonType> result = dialog.showAndWait();
        if ((result.get() == selectButton)) {
            if (m.equals(Buttons.NEW_GAME)) {
                final String s = map.entrySet().stream()
                                               .map(b -> b.getValue())
                                               .filter(t -> !t.getX().getText().equals(""))
                                               .findFirst()
                                               .get().getX().getText();

                this.controller.get().createGame(s, map.values().stream()
                                                                .filter(a -> a.getX().getText().equals(s))
                                                                .findFirst()
                                                                .get().getY());

           } else if (m.equals((Buttons.LOAD_GAME))) {
               this.controller.get().loadGame(this.selProfile.get());
           }
        }
    }
}

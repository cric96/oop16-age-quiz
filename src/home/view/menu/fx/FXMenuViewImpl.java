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
import home.view.MessageType;
import home.view.fx.AbstractFXView;
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
        this.selProfile = Optional.empty();
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
        final Map<ProfileButton, Pair<Profile, Pair<TextField, Text>>> map = new HashMap<>();
        final Text deleteDataMessage = new Text("Warning! all saved data will be deleted.\nContine?");
        deleteDataMessage.setVisible(false);

        final HBox container = new HBox(boxPadding);
        container.layoutYProperty().set(yLayoutBox);

        final VBox root = new VBox();
        root.getChildren().addAll(container);

        final ButtonType selectButton = new ButtonType("Select");
        final ButtonType createButton = new ButtonType("Create");
        final Alert dialog = new Alert(AlertType.NONE);
        dialog.setTitle(m.getText());
        dialog.getButtonTypes().setAll(ButtonType.CLOSE);
        dialog.getDialogPane().setContent(root);
        dialog.initOwner(this.getScene().getWindow());

        if (m.equals(Buttons.NEW_GAME)) {
            root.getChildren().add(deleteDataMessage); 
        } 
        profiles.forEach(e -> {
            try {
                final VBox box = new VBox(20);
                final ProfileButton buttonP = new ProfileButton(e);
                buttonP.setPrefWidth(buttonWidth);

                final TextField name = new TextField();
                name.setVisible(false);

                final Text date = new Text(e.getSaveDate());
                date.setVisible(false);

                box.getChildren().add(buttonP);

                if (m.equals(Buttons.LOAD_GAME)) {
                    box.getChildren().add(date);
                    if (!e.isEnabled()) {
                        buttonP.setDisable(true);
                    }
                } else if (m.equals(Buttons.NEW_GAME)) {
                    box.getChildren().add(name);
                }

                map.put(buttonP, Pair.createPair(e, Pair.createPair(name, date)));

                /*ON MOUSE CLICKED*/
                buttonP.setOnMouseClicked(x -> {
                    if (m.equals(Buttons.NEW_GAME)) {
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
                        this.selProfile = Optional.of(e);
                    } else if (m.equals((Buttons.LOAD_GAME))) {
                        dialog.getButtonTypes().setAll(selectButton);
                        map.get(buttonP).getY().getY().setVisible(true);
                        map.values()
                           .stream()
                           .filter(y -> !y.equals(map.get(buttonP)))
                           .forEach(a -> {
                               a.getY().getY().setVisible(false);
                               a.getY().getY().setText(e.getSaveDate());
                           });
                        this.selProfile = Optional.of(e);
                        dialog.getButtonTypes().setAll(selectButton);
                    }
                });
                container.getChildren().add(box);
            } catch (IOException x) {
                showMessage(x.getMessage(), MessageType.ERROR);
            }
        });


        final Optional<ButtonType> result = dialog.showAndWait();
        if (result.get().equals(createButton)) {
                this.controller.get().createGame(map.entrySet().stream()
                                                               .map(b -> b.getValue())
                                                               .filter(t -> !t.getY().getX().getText().equals(""))
                                                               .findFirst()
                                                               .get().getY().getX().getText(), this.selProfile.orElseThrow(() -> new IllegalStateException()));
        } else if (result.get() .equals(selectButton)) {
            this.controller.get().loadGame(this.selProfile.get());
        }

    }
    //RICHI GUARDA SE TI PUò FARE COMODO! VIENE CHIAMATA OGNI VOLTA CHE LA VIEW DEVE ESSERE MOSTRATA
    @Override
    public void show() {
        // TODO Auto-generated method stub
    }
}

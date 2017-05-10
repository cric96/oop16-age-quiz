package home.view.menu.fx;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import home.controller.MenuController;
import home.controller.profile.Profile;
import home.utility.BundleLanguageManager;
import home.utility.Pair;
import home.utility.Utility;
import home.view.menu.Buttons;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
* a class to create a show/load dialog in javafx.
*/
public class MenuDialog {
    private Optional<Profile> selProfile = Optional.empty();
    /**
     * 
     * @param profiles to show.
     * @param controller the controller of menu. to call method.
     * @param mode modality: LOAD_GAME, NEW_GAME
     * @param window owner of this dialog.
     * @throws IllegalArgumentException if fxMenuVewImpl or controller are Optional.empty()
     */
    public void show(final Set<Profile> profiles, 
                     final Optional<MenuController> controller,
                     final Buttons mode, 
                     final Window window) {

        if (!controller.isPresent()) {
            throw new IllegalArgumentException();
        }
        final ResourceBundle buttonText = BundleLanguageManager.get().getBundle("ButtonBundle");
        final ResourceBundle labelTest = BundleLanguageManager.get().getBundle("LabelBundle");
        final int boxPadding = 10;
        final int buttonWidth = 200;
        final int yLayoutBox = 10;
        final ButtonType createButton = new ButtonType(buttonText.getString("CREATE"));
        final ButtonType selectButton = new ButtonType(buttonText.getString("LOAD"));
        final Map<ProfileButton, Pair<Profile, Pair<TextField, Text>>> map = new HashMap<>();

        final Text deleteDataMessage = new Text(labelTest.getString("WARNING"));
        deleteDataMessage.setVisible(false);
        final Font font = Utility.getGeneralFont();
        deleteDataMessage.setFont(font);

        final HBox container = new HBox(boxPadding);
        container.layoutYProperty().set(yLayoutBox);

        final VBox root = new VBox();

        root.getChildren().addAll(container);

        final Alert dialog = new Alert(AlertType.NONE);
        dialog.setTitle(mode.toString());
        dialog.getButtonTypes().setAll(ButtonType.CANCEL);
        dialog.getDialogPane().setContent(root);
        dialog.initOwner(window);
        dialog.setResizable(false);
        dialog.initStyle(StageStyle.DECORATED);
        dialog.initModality(Modality.APPLICATION_MODAL);
        if (mode.equals(Buttons.NEW_GAME)) {
            root.getChildren().add(deleteDataMessage); 
        }

        for (final Profile profile: profiles) {
            final VBox box = new VBox(20);
            final ProfileButton buttonP = new ProfileButton(profile, mode);
            buttonP.setPrefWidth(buttonWidth);

            final TextField name = new TextField();
            name.setVisible(false);

            final Text date = new Text(profile.getSaveDate());
            date.setFont(font);
            date.setVisible(false);

            box.getChildren().add(buttonP);

            if (mode.equals(Buttons.LOAD_GAME)) {
                box.getChildren().add(date);
                if (!profile.isEnabled()) {
                    buttonP.setDisable(true);
                }
            } else if (mode.equals(Buttons.NEW_GAME)) {
                box.getChildren().add(name);
            }

            map.put(buttonP, Pair.createPair(profile, Pair.createPair(name, date)));

            /*ON MOUSE CLICKED*/
            buttonP.setOnMouseClicked(button -> {
                setSelProfile(Optional.of(profile));

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

                    if (profile.getName().isPresent()) {
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
                           a.getY().getY().setText(profile.getSaveDate());
                       });
                    dialog.getButtonTypes().setAll(selectButton);
                }
            });
            container.getChildren().add(box);
        }

        final Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.get().equals(createButton)) {
                    controller.get().createGame(map.entrySet().stream()
                                                              .map(b -> b.getValue())
                                                              .filter(t -> !t.getY().getX().getText().equals(""))
                                                              .findFirst()
                                                              .get().getY().getX().getText(), 
                                                              selProfile.get());
            } else if (result.get() .equals(selectButton)) {
                controller.get().loadGame(selProfile.get());
            }
        }
    }

    private void setSelProfile(final Optional<Profile> profile) {
        this.selProfile = profile;
    }
}

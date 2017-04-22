package home.view.menu;

import javafx.geometry.NodeOrientation;
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
import home.view.FXMessageType;
import home.view.FXView;
import home.view.FXViewImpl;
import home.view.MainView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;

/**
 * Implementation of Menu view in javafx.
 */
public class MenuViewImpl extends FXViewImpl implements MenuView{
    private Optional<MenuController> controller;

    public MenuViewImpl() {
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
        showLoadNew(MODE.LOAD, profiles);
    }

    @Override
    public void showNewGame(final Set<Profile> profiles) {
        showLoadNew(MODE.NEW, profiles);
    }

    private void showLoadNew(MODE m, final Set<Profile> profiles){
        Map<ProfileDialogImg, Pair<TextField, Profile>> map = new HashMap<>();
        final VBox root = new VBox();
        final HBox container = new HBox(20);
        container.layoutYProperty().set(20);
        final Text allertMessage = new Text("Attenzione tutti i salvataggi di questo slot verranno eliminati\nContinuare?");
        allertMessage.setVisible(false);


        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Select slot");
        alert.getDialogPane().setContent(root);
        
        final ButtonType selectButton = new ButtonType(m.equals(MODE.LOAD) ? "Select": "Create");
        alert.getButtonTypes().setAll(selectButton);

        profiles.forEach(e -> {
            try {
                ProfileDialogImg buttonP = new ProfileDialogImg(e);
                if(m.equals(MODE.LOAD) && !e.isEnabled()){
                    buttonP.setDisable(true);
                }
                buttonP.setPrefWidth(200);
                final VBox box = new VBox(20);
                TextField name = new TextField();
                name.setVisible(false);
                box.getChildren().add(buttonP);
                if(m.equals(MODE.NEW)){
                    box.getChildren().add(name);
                }
                map.put(buttonP, Pair.createPair(name, e));

                buttonP.setOnMouseClicked(x -> {
                    map.get(buttonP).getX().setVisible(true);
                    map.values()
                       .stream()
                       .filter(y -> !y.equals(map.get(buttonP)))
                       .forEach(a -> {a.getX().setVisible(false); a.getX().setText("");});
                    if(m.equals(MODE.NEW)) {
                        if (e.getName().isPresent()){
                            allertMessage.setVisible(true);
                        }else{
                            allertMessage.setVisible(false);
                        }
                    }
                });
                container.getChildren().add(box);
            } catch (IOException x) {
                FXView.showMessage(x.getMessage(), FXMessageType.ERROR);
            }
        });

        root.getChildren().add(container);
        if(m.equals(MODE.NEW)){
            root.getChildren().add(allertMessage);
        }
        MainView.showStage(alert);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == selectButton){
           if(m.equals(MODE.NEW)){
                String s = map.entrySet().stream().map(b -> b.getValue()).filter(t -> !t.getX().getText().equals("")).findFirst().get().getX().getText();
                this.controller.get().createGame(s, map.values().stream().filter(a -> a.getX().getText().equals(s)).findFirst().get().getY());
            }
        }
    }

    enum MODE {
        NEW,

        LOAD;
    }
   
}

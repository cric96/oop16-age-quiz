package home.view.menu;

import java.awt.Window;
import java.io.IOException;

import java.util.Set;
import javafx.scene.layout.Pane;

import home.controller.menu.MenuController;
import home.controller.menu.Profile;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * Implementation of Menu view in javafx.
 */
public class MenuViewImpl extends Scene  implements MenuView {
    private static MenuController controller;
    /** 
     * @throws IOException if background load gone wrong.
     */
    public MenuViewImpl() throws IOException {
        super(new GameMenu(controller));
    }

    @Override
    public void attachOn(final MenuController controller) {
        MenuViewImpl.controller = controller;
    }

//    public static void showSaved(final Set<Boolean> profiles){
//        Alert a = new Alert(AlertType.NONE);
//        a.setTitle("Select a profile");
//        a.initOwner(null);
//        Pane box = new Pane();
//        a.getDialogPane().getChildren().add(box);
//        profiles.forEach(e -> {
//            try {
//                ProfileDialogImg p = new ProfileDialogImg(e);
//                p.setDisable(false);
//                box.getChildren().add(new ProfileDialogImg(e));
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        });
//        a.show();
//    }

    @Override
    public void showSavedGames(final Set<Profile> profiles) {

    }

    @Override
    public void showNewGame(final Set<Profile> profiles) {
        // TODO Auto-generated method stub

    }
}

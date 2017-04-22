package home.view;

import java.awt.RenderingHints.Key;
import java.util.Optional;
import java.util.Set;

import home.controller.Controller;
import home.controller.menu.MenuController;
import home.controller.menu.MenuControllerImpl;
import home.controller.menu.Profile;
import home.utility.Pair;
import home.view.menu.GameMenu;
import home.view.menu.MainMenuImpl;
import home.view.menu.MenuView;
import home.view.menu.MenuViewImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;



/**
 * 
 * View implementation of MainView.
 *
 */
public class MainView extends Application {
    private static Stage s;
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setFullScreen(true);
        this.s = primaryStage;
        primaryStage.setScene(new Scene(new GameMenu(new MenuControllerImpl())));
        primaryStage.setResizable(false);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setTitle(MainMenuImpl.getTitle());
//        FXContainer.getContainer().setStage(primaryStage);
//        MenuView m = new MenuViewImpl();
//        MenuControllerImpl cont = new MenuControllerImpl(new MenuViewImpl());
//        m.attachOn(cont);
//        Pair<ViewType, Controller> menuController = Pair.createPair(ViewType.MENU, cont);
//        FXContainer.getContainer().addController(menuController);
//        FXContainer.getContainer().changeDisplay(ViewType.MENU);

        primaryStage.show();
    }
    
    /**
     * display specific dialog with a message for the user. 
     * @param message to show.
     * @param type is used to show different window message.
     * @return 
     */
    public static void showMessage(String message, MessageType type) {
        Alert alert = new Alert(type.getAlertType());
        alert.setTitle(type.getMessageTitle());
        alert.setHeaderText(message);
        alert.initOwner(MainView.s);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK && type.equals(MessageType.EXIT)){
            System.exit(0);
        }
    }

    public static void main(String[] args){
        launch(args);
    }
 }


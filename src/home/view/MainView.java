package home.view;

import java.util.Optional;
import java.util.Set;

import home.controller.Controller;
import home.controller.menu.MenuController;
import home.controller.menu.MenuControllerImpl;
import home.utility.Pair;
import home.view.menu.MainMenuImpl;

import home.view.menu.MenuViewImpl;
import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * 
 * View implementation of MainView.
 *
 */
public class MainView extends Application {
    private static Stage stage;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setFullScreen(true);
        MainView.stage = primaryStage;
        //primaryStage.setScene(new Scene(new GameMenu(new MenuControllerImpl())));
        primaryStage.setResizable(false);
        //primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setTitle(MainMenuImpl.getTitle());
        FXContainer.getContainer().setStage(MainView.stage);
        MenuViewImpl menu = new MenuViewImpl();
        MenuController cont = new MenuControllerImpl(menu);
        //MainView.getStage().setScene(menu.getScene());
        Pair<ViewType, Controller> menuController = Pair.createPair(ViewType.MENU, cont);
        FXContainer.getContainer().addController(menuController);
        FXContainer.getContainer().changeDisplay(ViewType.MENU);

        primaryStage.show();
    }
 
//    public static Stage getStage(){
//        return stage;
//
//    }

    public static void showStage(Alert alert){
        alert.initOwner(stage);
    }
    
    public static void main(String[] args){
        launch(args);
    }
 }


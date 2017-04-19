package home.view;

import java.util.Optional;

import home.view.menu.MainMenuImpl;
import home.view.menu.MenuViewImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;



/**
 * 
 * View implementation of MainView.
 *
 */
public class MainView extends Application {
    private static Stage primaryStage;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        MainView.primaryStage = primaryStage;
        primaryStage.setTitle(MainMenuImpl.getTitle());
        primaryStage.setResizable(false);
        primaryStage.setScene(new MenuViewImpl());
        primaryStage.show();
        FXContainer.getContainer().setStage(primaryStage);
    }

    public static void setScene(final Scene scene) {
        primaryStage.setScene(scene);
    }

    public static void main(String[] args){
        launch(args);
    }
 }


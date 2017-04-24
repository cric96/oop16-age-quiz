package home.view;

import home.controller.MenuController;
import home.controller.MenuControllerImpl;
import home.utility.Pair;
import home.view.fx.FXContainer;
import home.view.menu.MainMenuImpl;
import home.view.menu.fx.FXMenuViewImpl;
import javafx.application.Application;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 * 
 * AppTest.
 *
 */
public class App extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final FXMenuViewImpl menu = new FXMenuViewImpl(); // MenuView
        final MenuController cont = new MenuControllerImpl(menu); //MenuController
        FXContainer.getContainer().setStage(primaryStage);
        FXContainer.getContainer().addController(Pair.createPair(ViewType.MENU, cont)); //add a controller 
        FXContainer.getContainer().changeDisplay(ViewType.MENU); //show Menu
        primaryStage.show();
    }
 
    /**
     * main.
     * @param args args.
     */
    public static void main(final String[] args) {
        launch(args);
    }
 }


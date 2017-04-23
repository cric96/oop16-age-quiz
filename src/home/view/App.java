package home.view;

import home.controller.menu.MenuController;
import home.controller.menu.MenuControllerImpl;
import home.utility.Pair;
import home.view.fx.FXContainer;
import home.view.menu.MainMenuImpl;
import home.view.menu.fx.FXMenuViewImpl;
import javafx.application.Application;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 * 
 * View implementation of MainView.
 *
 */
public class App extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setTitle(MainMenuImpl.getTitle());
        FXContainer.getContainer().setStage(primaryStage);
        final FXMenuViewImpl menu = new FXMenuViewImpl(); // MenuView
        final MenuController cont = new MenuControllerImpl(menu); //MenuController
        //final Pair<ViewType, Controller> menuController = Pair.createPair(ViewType.MENU, cont);
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


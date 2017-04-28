package home.view;

import home.controller.ControllerFactory;
import home.controller.MenuController;
import home.controller.WorldController;
import home.utility.Pair;
import home.view.debug.ConsoleViewFactory;
import home.view.menu.MenuView;
import home.view.menu.fx.FXMenuViewImpl;
import home.view.world.WorldView;
import home.view.world.fx.FXWorldViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * AppTest.
 *
 */
public class App extends Application {

    @Override
    public void start(final Stage primaryStage) {
        final MenuView menu = new FXMenuViewImpl(); // MenuView
        final MenuView menuConsole = ConsoleViewFactory.createMenuView();
        final MenuController cont = ControllerFactory.create().createMenuController(/*menu,*/ menu); //MenuController
        final WorldView world = new FXWorldViewImpl();
        final WorldView worldConsole = ConsoleViewFactory.createWolrdView();
        final WorldController worldController = ControllerFactory.create().createWorldController(/*world ,*/worldConsole);
        FXContainer.getContainer().setStage(primaryStage);
        FXContainer.getContainer().addController(Pair.createPair(ViewType.MENU, cont)); //add a controller 
        FXContainer.getContainer().addController(Pair.createPair(ViewType.WORLD, worldController));
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


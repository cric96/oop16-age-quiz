package home.main;

import home.controller.ControllerFactory;
import home.utility.Pair;
import home.view.App;
import home.view.Container;
import home.view.ViewType;
import home.view.debug.ConsoleViewFactory;
import home.view.menu.MenuView;
import home.view.menu.fx.FXMenuViewImpl;
import home.view.quiz.QuizView;
import home.view.quiz.QuizViewImpl;
import home.view.world.WorldView;
import home.view.world.fx.FXWorldViewImpl;
import javafx.application.Application;

/**
 * This class is used to start the videogame
 * and to set some values.
 * 
 */
public final class Main {
    private static final boolean DEBUG = false;
    private Main() { }
    /** 
     * @param args
     *          not used in this contest 
     */
    public static void main(final String[] args) {
        Application.launch(App.class);
        
        Container.getContainer().changeDisplay(ViewType.MENU);
    }
    
}

package home.view.fx;

import java.io.IOException;

import home.view.menu.MenuView;
import home.view.menu.fx.FXMenuViewImpl;
import home.view.quiz.QuizView;
import home.view.quiz.FXQuizViewImpl;
import home.view.world.WorldView;
import home.view.world.fx.FXWorldViewImpl;

/**
 * views factory.
 */
public final class ViewFactory {
    private static final ViewFactory SINGLETON = new ViewFactory();

    private ViewFactory() { }

    /**
     * 
     * @return
     *      a factory of views.
     */
    public static ViewFactory getFactory() {
        return ViewFactory.SINGLETON;
    }
    /**
     * 
     * @return new MenuView
     */
    public MenuView createMenuView() {
        return new FXMenuViewImpl();
    }

    /**
     * 
     * @return new WorldView
     */
    public WorldView createWorldView() {
        return new FXWorldViewImpl();
    }

    /**
     * 
     * @return new QuizView
     * @throws IOException 
     */
    public QuizView createQuizView() throws IOException {
        return new FXQuizViewImpl();
    }
}

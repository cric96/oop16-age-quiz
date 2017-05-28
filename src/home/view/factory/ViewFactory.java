package home.view.factory;

import home.view.menu.MenuView;
import home.view.quiz.QuizView;
import home.view.world.WorldView;

/**
 * views factory.
 */
public final class ViewFactory {
    private ViewFactory() { }

    /**
     * 
     * @return 
     *  new MenuView
     */
    public static MenuView createMenuView() {
        return new MenuViewImpl();
    }

    /**
     * 
     * @return
     *  new WorldView
     */
    public static WorldView createWorldView() {
        return new WorldViewImpl();
    }

    /**
     * 
     * @return 
     *  new QuizView
     */

    public static QuizView createQuizView()  {
        return new QuizViewImpl();
    }
}

package home.view.debug;

import home.view.menu.MenuView;
import home.view.quiz.QuizView;
import home.view.world.WorldView;

/**
 * a factory to create a simple view used to debug.
 */
public interface ConsoleViewFactory {
    /**
     * @return
     *  a Console menu view used to debug
     */
    static MenuView createMenuView() {
        return new ConsoleMenuViewImpl();
    }
    /**
     * @return 
     *  a Console world view used to debug
     */
    static WorldView createWolrdView() {
        return new ConsoleWolrdViewImpl();
    }
    /**
     * @return 
     *  a Console world view used to debug
     */
    static QuizView createQuizView() {
        return new ConsoleQuizViewImpl();
    }
}

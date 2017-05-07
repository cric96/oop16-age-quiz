package home.view.debug;

import home.controller.ControllerFactory;
import home.utility.Pair;
import home.view.Container;
import home.view.ViewType;
import home.view.menu.MenuView;
import home.view.quiz.QuizView;
import home.view.world.WorldView;

/**
 * a platform to test the application with the console view.
 */
public final class DebugApplication {
    private DebugApplication() { }
    /**
     * create an instance of application.
     */
    public static void launch() {
        createConsole();
        Container.getContainer().changeDisplay(ViewType.MENU);
    }

    private static void createConsole() {
        attachOnController(ConsoleViewFactory.createMenuView(),
                           ConsoleViewFactory.createWolrdView(),
                           ConsoleViewFactory.createQuizView());
    }
    private static void attachOnController(final MenuView menu, final WorldView world, final QuizView quiz) {
        final Container container = Container.getContainer();
        final ControllerFactory factory = ControllerFactory.create();
        container.addController(Pair.createPair(ViewType.MENU, factory.createMenuController(menu)));
        container.addController(Pair.createPair(ViewType.WORLD, factory.createWorldController(world)));
        container.addController(Pair.createPair(ViewType.QUIZ, factory.createQuizController(quiz)));
    }
}

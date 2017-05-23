package home.view.debug;

import home.controller.ControllerFactory;
import home.utility.Pair;
import home.view.ViewType;
import home.view.container.Container;

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
        final Container container = Container.getContainer();
        final ControllerFactory cFactory = ControllerFactory.create();
        container.addController(Pair.createPair(ViewType.MENU, cFactory.createMenuController(ConsoleViewFactory.createMenuView())));
        container.addController(Pair.createPair(ViewType.WORLD, cFactory.createWorldController(ConsoleViewFactory.createWolrdView())));
        container.addController(Pair.createPair(ViewType.QUIZ, cFactory.createQuizController(ConsoleViewFactory.createQuizView())));
    }
}

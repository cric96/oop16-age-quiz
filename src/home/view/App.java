package home.view;

import home.controller.ControllerFactory;
import home.utility.Pair;
import home.view.debug.ConsoleViewFactory;
import home.view.menu.MenuView;
import home.view.menu.fx.FXMenuViewImpl;
import home.view.quiz.QuizView;
import home.view.quiz.QuizViewImpl;
import home.view.world.WorldView;
import home.view.world.fx.FXWorldViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * what to launch to create the application.
 */
public class App extends Application {
    private static final boolean DEBUG = false;
    @Override
    public void start(final Stage primaryStage) {
        FXContainer.getContainer().setStage(primaryStage);
        if (DEBUG) {
            createConsole();
        } else {
            createVisual();
        }
        FXContainer.getContainer().changeDisplay(ViewType.MENU);
        primaryStage.show();
    }
    private static void createVisual() {
        attachOnController(new FXMenuViewImpl(),
                           new FXWorldViewImpl(),
                           new QuizViewImpl());
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


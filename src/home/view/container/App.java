package home.view.container;

import home.controller.ControllerFactory;
import home.utility.Pair;
import home.view.ViewType;
import home.view.factory.ViewFactory;
import home.view.menu.MenuView;
import home.view.quiz.QuizView;
import home.view.world.WorldView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * what to launch to create the application.
 */
public class App extends Application {
    @Override
    public void start(final Stage primaryStage) {
        FXContainer.getContainer().setStage(primaryStage);
        createVisual();
        FXContainer.getContainer().changeDisplay(ViewType.MENU);
        primaryStage.show();
    }

    private static void createVisual() {
        attachOnController(ViewFactory.createMenuView(),
                           ViewFactory.createWorldView(),
                           ViewFactory.createQuizView());
    }

    private static void attachOnController(final MenuView menu, final WorldView world, final QuizView quiz) {
        final Container container = Container.getContainer();
        final ControllerFactory factory = ControllerFactory.create();
        container.addController(Pair.createPair(ViewType.MENU, factory.createMenuController(menu)));
        container.addController(Pair.createPair(ViewType.WORLD, factory.createWorldController(world)));
        container.addController(Pair.createPair(ViewType.QUIZ, factory.createQuizController(quiz)));
    }
 }


package home.view;

import java.io.IOException;

import home.controller.ControllerFactory;
import home.utility.Pair;
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
    @Override
    public void start(final Stage primaryStage) {
        FXContainer.getContainer().setStage(primaryStage);
        try {
            createVisual();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXContainer.getContainer().changeDisplay(ViewType.MENU);
        primaryStage.show();
    }

    private static void createVisual() throws IOException {
        attachOnController(new FXMenuViewImpl(),
                           new FXWorldViewImpl(),
                           new QuizViewImpl());
    }
    private static void attachOnController(final MenuView menu, final WorldView world, final QuizView quiz) {
        final Container container = Container.getContainer();
        final ControllerFactory factory = ControllerFactory.create();
        container.addController(Pair.createPair(ViewType.MENU, factory.createMenuController(menu)));
        container.addController(Pair.createPair(ViewType.WORLD, factory.createWorldController(world)));
        container.addController(Pair.createPair(ViewType.QUIZ, factory.createQuizController(quiz)));
    }
 }


package home.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import home.view.menu.MenuView;
import home.view.quiz.QuizView;
import home.view.world.WorldView;

/**
 * a factory to create controller.
 */
public final class ControllerFactory {
    private static final ControllerFactory SINGLETON = new ControllerFactory();
    /**
     * 
     * @return
     *  a factory of controller
     */
    public static ControllerFactory create() {
        return ControllerFactory.SINGLETON;
    }
    /**
     * create a new menu controller.
     * @param views
     *  the views that you want to attach on this controller
     * @return
     *  the controller created
     */
    public Controller createMenuController(final MenuView ... views) {
        final AbstractObserver observer = new MenuObserverImpl(Arrays.stream(views).collect(Collectors.toSet()));
        return new ControllerImpl(observer);
    }
    /**
     * create a new quiz controller.
     * @param views
     *  the views that you want to attach on this controller
     * @return
     *  the controller created
     */
    public Controller createQuizController(final QuizView ... views) {
        final AbstractObserver observer = new QuizObserverImpl(Arrays.stream(views).collect(Collectors.toSet()));
        return new ControllerImpl(observer);
    }

    /**
     * create a new world controller.
     * @param views
     *  the views that you want to attach on this controller
     * @return
     *  the controller created
     */
    public Controller createWorldController(final WorldView ...views) {
        final AbstractObserver observer = new WorldObserverImpl(Arrays.stream(views).collect(Collectors.toSet()));
        return new ControllerImpl(observer);
    }
}

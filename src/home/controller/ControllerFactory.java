package home.controller;

import java.util.Objects;

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
    public MenuController createMenuController(final MenuView ... views) {
        return new MenuControllerImpl(views);
    }
    /**
     * create a new quiz controller.
     * @param views
     *  the views that you want to attach on this controller
     * @return
     *  the controller created
     */
    public QuizController createQuizController(final QuizView ... views) {
        Objects.requireNonNull(views);
        return new QuizControllerImpl(views);
    }

    /**
     * create a new world controller.
     * @param views
     *  the views that you want to attach on this controller
     * @return
     *  the controller created
     */
    public WorldController createWorldController(final WorldView ...views) {
        Objects.requireNonNull(views);
        return new WorldControllerImpl(views);
    }
}

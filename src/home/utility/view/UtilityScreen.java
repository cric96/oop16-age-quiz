package home.utility.view;

import javafx.stage.Screen;

/**
 * class used to get the size of screen.
 */
public final class UtilityScreen {
    private static final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
    private static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();

    private UtilityScreen() { }

    /**
     * @return the visual screenWidth
     */
    public static double getScreenWidth() {
        return SCREEN_WIDTH;
    }

    /**
     * @return the visual screenHeight
     */
    public static double getScreenHeight() {
        return SCREEN_HEIGHT;
    }

}

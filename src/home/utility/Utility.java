package home.utility;

import home.view.Fonts;
import javafx.scene.text.Font;
import javafx.stage.Screen;

/**
 * some method that can be useful.
 */
public final class Utility {
    private static final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
    private static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
    
    private Utility() { }
    /**
     * check if some object in input is null.
     * @param objects
     *  the array of object
     * @return
     *  true if one of these is null
     */
    public static boolean checkNullOb(final Object ... objects) {
        for (final Object o : objects) {
            if (o == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * used to set this font to all of text in the game.
     * @param dimension of the font
     * @return the general Font
     */
    public static Font getGeneralFont(final int dimension) {
        return Font.loadFont(ResourceManager.load(Fonts.COURIER_NEW.getFontPath()).toExternalForm(), dimension);
    }

    /**
     * used to set this font to all of text in the game with a default size.
     * @return the general Font
     */
    public static Font getGeneralFont() {
        final int defalutSize = 15;
        return Font.loadFont(ResourceManager.load(Fonts.COURIER_NEW.getFontPath()).toExternalForm(), defalutSize);
    }
    
    public static Font titleFont(int size){
        return Font.loadFont(ResourceManager.load(Fonts.FAITH_COLLAPSING.getFontPath()).toExternalForm(), size);
    }

    /**
     *
     * @return title of the game
     */
    public static String getTitle() {
        return "Age of Quiz";

    }

    /**
     * @return the screenWidth
     */
    public static double getScreenWidth() {
        return SCREEN_WIDTH;
    }
    /**
     * @return the screenHeight
     */
    public static double getScreenHeight() {
        return SCREEN_HEIGHT;
    }
}

package home.utility;

import javafx.scene.text.Font;

/**
 * some method that can be useful.
 */
public final class Utility {
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
        return Font.font("Courier New", dimension);
    }

    /**
     * used to set this font to all of text in the game with a default size.
     * @return the general Font
     */
    public static Font getGeneralFont() {
        return Font.font("Courier New");
    }

    /**
     *
     * @return title of the game
     */
    public static String getTitle() {
        return "Age of Quiz";

    }
}

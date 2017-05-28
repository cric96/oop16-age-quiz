package home.utility.view;

import home.utility.ResourceManager;
import home.view.Fonts;
import javafx.scene.text.Font;
/**
 * a class used to manage the font in the game.
 *
 */
public final class FontManager {
    private FontManager() { }
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

    /**
     * the title font.
     * @param size 
     *         size of font
     * @return 
     *  font created
     */
    public static Font titleFont(final int size) {
        return Font.loadFont(ResourceManager.load(Fonts.FAITH_COLLAPSING.getFontPath()).toExternalForm(), size);
    }
}

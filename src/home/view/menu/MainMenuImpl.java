package home.view.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * implementation of MainMenu interface.
 *
 */
public final class MainMenuImpl {
    private static final List<Buttons> BUTTONS_NAME = Arrays.asList(Buttons.values());
    private static final String MENU_BACKGROUND = "res/images/backgroundMenu.jpg";
    private static final String TITLE = "Age of Quiz";

    private MainMenuImpl() { }
 
    /**
     * @return the string of buttons in the menu.
     */
    public static List<String> buttonsNameList() {
        return BUTTONS_NAME.stream().map(a -> a.getText()).collect(Collectors.toList());
    }

    /**
     * @return the path of background image.
     */
    public static String backgroundPath() {
        return MENU_BACKGROUND;
    }

    /**
     * @return the title of window.
     */
    public static String getTitle() {
        return TITLE;
    }

}

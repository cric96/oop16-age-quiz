package home.view.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * implementation of MainMenu interface.
 *
 */
public final class MainMenuImpl {
    private static final List<Buttons> buttonsName = Arrays.asList(Buttons.values());
    private static final String menuBackground = "res/images/backgroundMenu.jpg";
    private static final String title = "Age of Quiz";

    private MainMenuImpl() { }
 
    /**
     * @return the string of buttons in the menu.
     */
    public static List<String> buttonsNameList() {
        return buttonsName.stream().map(a -> a.getText()).collect(Collectors.toList());
    }

    /**
     * @return the path of background image.
     */
    public static String backgroundPath() {
        return menuBackground;
    }

    public static String getTitle() {
        return title;
    }

}

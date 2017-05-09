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

    private MainMenuImpl() { }
 
    /**
     * @return the string of buttons in the menu.
     */
    public static List<String> buttonsNameList() {
        return BUTTONS_NAME.stream().map(a -> a.toString()).collect(Collectors.toList());
    }
}

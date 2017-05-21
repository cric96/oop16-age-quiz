package home.view.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * class used to create a menu (list of possible buttons).
 */
public final class MainMenuImpl {
    private static final List<Buttons> BUTTONS_NAME = Arrays.asList(Buttons.values());

    private MainMenuImpl() { }
 
    /**
     * @return the name of buttons in the menu.
     */
    public static List<String> buttonsNameList() {
        return BUTTONS_NAME.stream().map(a -> a.toString()).collect(Collectors.toList());
    }
}

package home.view.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * implementation of MainMenu interface.
 *
 */
public class MainMenuImpl implements MainMenu {
    private final List<Buttons> buttonsName = Arrays.asList(Buttons.values());
    private final String menuBackground = "res/images/backgroundMenu.jpg";
    private final String title = "Age of Quitz";

    @Override
    public List<String> buttonsNameList() {
        return buttonsName.stream().map(a -> a.getText()).collect(Collectors.toList());
    }

    /**
     * @return the path of background image.
     */
    public String backgroundPath() {
        return menuBackground;
    }

    @Override
    public String getTitle() {
        return title;
    }

}

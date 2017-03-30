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
    private final List<MenuButtons> buttonsName = Arrays.asList(MenuButtons.values());
    private final String menuBackground = "";
    private final String title = "Age of Quitz";

    @Override
    public List<Optional<String>> buttonsNameList() {
        return buttonsName.stream().map(a -> Optional.of(a.getText())).collect(Collectors.toList());
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

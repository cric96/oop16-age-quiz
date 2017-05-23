package home.view.fx.button;

import home.controller.observer.MenuObserver;
import home.controller.profile.Profile;
import home.view.fx.parent.ParentMenu;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * static factory for buttons inside menu.
 */
public final class MenuButtonFactory {
    private MenuButtonFactory() { }

    /**
     * 
     * @param name
     *          the text of button
     * @param color
     *          the color of over-effect
     * @return
     *          the menu button
     */
    public static MenuButton createMenuButton(final String name, final Color color) {
        return new MenuButtonImpl(name, Color.RED);
    }

    /**
     * create new instance of button used in load game dialog.
     * @param profile associated with this button
     * @return the profile button 
     */
    public static Button createProfileButtonLoadGame(final Profile profile) {
        return new ButtonProfileLoadGame(profile);
    }

    /**
     * create new instance of button used in new game dialog.
     * @param profile associated with this button
     * @return the profile button
     */
    public static Button createProfileButtonNewGame(final Profile profile) {
        return new ButtonProfileNewGame(profile);
    }

    /**
     * create new instance of languageBox.
     * @param parent 
     *          to refresh text
     * @param menu
     *      observer of this view
     * @return
     *      new languageBox selector
     */
    public static HBox createLanguageBox(final ParentMenu parent, final MenuObserver menu) {
        return new LanguageBox(parent, menu);
    }
}

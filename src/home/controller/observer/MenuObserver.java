package home.controller.observer;

import java.util.Locale;

import home.controller.profile.Profile;
/**
 * the observer of the menu. 
*/
public interface MenuObserver extends Observer {
    /**
     * notify at the observer that is pressed newGame.
     * the controller check its internal state and check what it can to do
     */
    void newGamePressed();
    /**
     * create a game associated with a profile.
     * @param name
     *  the name of profile
     * @param profile
     *  the profile to load
     */
    void createGame(String name, Profile profile);
    /**
     * notify at the observer that is pressed load game.
     * the controller check its internal state and check what it can to do
     */
    void loadGamePressed();
    /**
     * load a specific game.
     * @param profile
     *  the profile that you want to load
     */
    void loadGame(Profile profile);
    /**
     * tells that someone click on exit.
     */
    void exitPressed();
    /**
     * exit to the application.
     */
    void exitConfirmed();
    /**
     * the view tell the locale to change.
     * @param locale
     *  the locale passed
     */
    void changeLocale(Locale locale); 
}

package home.controller.observer;

import java.util.Locale;

import home.controller.profile.Profile;
/**
 * a menu observer.
*/
public interface MenuObserver extends Observer {
    /**
     * notify the observer that newGame has been pressed.
     * the controller check its internal state and check what it can do
     */
    void newGamePressed();
    /**
     * create a game associated to a profile.
     * @param name
     *  the name of the profile
     * @param profile
     *  the profile to load
     */
    void createGame(String name, Profile profile);
    /**
     * notify the observer that load game has been pressed.
     * the controller check its internal state and check what it can do
     */
    void loadGamePressed();
    /**
     * load a specific game.
     * @param profile
     *  the profile that you want to load
     */
    void loadGame(Profile profile);
    /**
     * tells that someone clicks on exit.
     */
    void exitPressed();
    /**
     * exit from the application.
     */
    void exitConfirmed();
    /**
     * the view tells the locale to change.
     * @param locale
     *  the locale passed
     */
    void changeLocale(Locale locale); 
}

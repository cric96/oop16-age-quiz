package home.controller;

import home.controller.profile.Profile;
/**
  a controller used to control a menu.
*/
public interface MenuController extends Controller {
    /**
     * notify at the controller that is pressed newGame.
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
     * notify at the controller that is pressed load game.
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
     * the controller exit and close the application.
     */
    void exitPressed();
}

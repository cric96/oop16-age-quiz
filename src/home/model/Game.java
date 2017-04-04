package home.model;

import java.io.File;

/**
 * define a session of game.
 * this interface be interested in maintain a unique instance of Kingdom
 * 
 */
public interface Game {
    /**
     * 
     * @return
     * an object of Game
     */
    static Game getGame() {
        return GameImpl.get();
    }
    /**
     * create a new game.
     */
    void newGame();
    /**
     * save the current game.
     * @param  save
     *  where put my current kingdom
     */
    void save(File save);
    /**
     * load a game.
     * @param load
     *  what to load
     */
    //TODO NON SO SE USARE FILE O UN OGGETTO APPOSTA
    void load(File load);
    /**
     * get the kingdom of current game.
     * @return
     *  the kingdom that define the current game
     */
    Kingdom getCurrentKingdom();
}

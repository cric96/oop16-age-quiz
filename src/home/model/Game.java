package home.model;

import java.io.File;

/**
 * an interface to define a session of game.
 */
public interface Game {
    
    /**
     * save the current game.
     */
    void save();
    /**
     * load a game.
     * @param save
     *  what to load
     */
    //TODO NON SO SE USARE FILE O UN OGGETTO APPOSTA
    void load(File save);
    /**
     * get the kingdom of current game.
     * @return
     *  the kingdom that define the current game
     */
    Kingdom getCurrentKingdom();
}

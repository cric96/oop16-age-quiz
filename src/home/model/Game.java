package home.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import home.model.building.BuildingType;
import home.model.quiz.QuizGame;

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
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    void save(File save) throws FileNotFoundException, IOException;
    /**
     * load a game.
     * @param load
     *  what to load
     * @throws IOException 
     * @throws FileNotFoundException 
     * @throws ClassNotFoundException 
     */
    void load(File load) throws FileNotFoundException, IOException, ClassNotFoundException;
    /**
     * get the kingdom of current game.
     * @return
     *  the kingdom that define the current game
     */
    Kingdom getCurrentKingdom();
    /**
     * @return
     *  optional.empty if there aren't no quiz , Optional.of if there is
     *  a quiz play and it's not finish
     * @throws IllegalStateException if there isn't kingdom created
     */
    Optional<QuizGame> getCurrentQuiz();
    /**
     * create a quiz.
     * @param building
     *  the building associated with this quiz
     */
    void createQuiz(BuildingType building);
    /**
     * end the current quiz and modify the status of kingdom.
     * @throws IllegalStateException if there isn't no quiz or if the quiz isn't finished
     */
    void endCurrentQuiz();
}

package home.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import home.model.building.BuildingType;
import home.model.kingdom.AgeUpKingdomStrategy;
import home.model.kingdom.Kingdom;
import home.model.quiz.QuizGame;

/**
 * define a session of the game.
 * this interface is done to maintain a unique instance of the Kingdom
 * during a game session.
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
     * @param type
     *  the type of strategy used by the kingdom
     */
    void newGame(AgeUpKingdomStrategy.Type type);
    /**
     * save the current game.
     * @param  save
     *  where put my current kingdom
     * @throws IOException 
     *  if there is some error to load the file
     * @throws FileNotFoundException 
     *  if there is some error to find file
     */
    void save(File save) throws FileNotFoundException, IOException;
    /**
     * load a game.
     * @param load
     *  what to load
     * @throws IOException 
     *  if there is some error to load the file
     * @throws FileNotFoundException 
     *  if there is some error to find the file
     * @throws ClassNotFoundException 
     *  if there is some error to cast the object in the file
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
     *  optional.empty if there are no more quiz , Optional.of if there is
     *  an active quiz and it's not been finished yet
     * @throws IllegalStateException 
     *  if there isn't kingdom created
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
     * @throws IllegalStateException 
     *  if there isn't no quiz or if the quiz isn't finished
     */
    void endCurrentQuiz();
}

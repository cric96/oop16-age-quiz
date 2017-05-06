package home.model.quiz;


import java.util.Map;

import home.model.query.Category;
import home.model.query.Query;
import home.model.status.StatusName;


/**
 *It allows to create a quiz (series of query) for a specific building, with specific category and
 *specific level.
 */

public interface QuizGame {

    /**
     * It shows the current query for this quiz, it should be a specific query for level and for category.
     * @return Query
     */
    Query showCurrentQuery();
    /**
     * It allows to hit an @param answer from those that are showed.
     * @param answer
     */
    void hitAnswer(String answer);
    /**
     * It allows to click for next question.
     */
    void next();
    /**
     * It tests the answer.
     * @return true if answer is correct, false otherwise
     */
    boolean isAnswerCorrect(); //questo metodo non c'è nell'UML
    /**
     * It will be true only if time for this specific quiz elapsed.
     * @return true if you have finished the questions for this game, false otherwise.
     */
    boolean isFinished();
    /**
     * When you have finished the quiz (you decide when or when isFinished is true,
     * it gets you the experience points.
     * @return an int that means how many XP you have reached with this quiz
     */
    int getXP(); //metodo cambiato di nome rispetto all'UML
    /**
     * At the end of the quiz the statistic will be better or worse, it depends on the number
     * of correct answers.
     * @return a set of pair of StatusName and the score to add or to remove from it
     */
    Map<StatusName, Integer> getStatusScore(); //metodo che non c'è sull'UML
    /**
     * @return number of seconds quiz will last 
     */
    int getQuizDuration();
    /**
     *Time elapsed so the quiz is stopped.
     */
    void setFinished();
    /**
     * @return the Category of the quiz
     */
    Category getCategory();
}

package home.model.quiz;

import home.model.level.Level;
import home.model.query.Category;
/**
 * Factory for quizGame.
 */
public final class QuizGameFactory {
    private QuizGameFactory() { };
    /**
     * 
     * @param cat
     *          the category for the quiz
     * @param level
     *          the level of the quiz
     * @return new advanced implementation of quizGame
     */
    public static QuizGame createQuizGameAdvanced(final Category cat, final Level level) {
        return new QuizGameAdvanced(cat, level);
    }
    /**
     * 
     * @param cat
     *          the category for the quiz.
     * @param level
     *          the level of the quiz.
     * @return new basic implementation of quizGame.
     */
    public static QuizGame createQuizGameBasic(final Category cat, final Level level) {
        return new QuizGameBasic(cat, level);
    }
}

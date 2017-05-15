package home.test;
import org.junit.Test;

import home.model.level.Level;
import home.model.query.Category;
import home.model.quiz.QuizGame;
import home.model.quiz.QuizGameFactory;
/**
 *
 */
public class QuizTest {
    /**
     * 
     */
    @Test
    public void test1() {
        final QuizGame quizGame = QuizGameFactory.createQuizGameAdvanced(Category.LIBERAL_ARTS, Level.Building.createBuildingLevel());
        System.out.println(quizGame.showCurrentQuery());
        System.out.println(quizGame.getStatusScore());
        System.out.println(quizGame.getXP());
        quizGame.hitAnswer("7");
        System.out.println(quizGame.getStatusScore());
        System.out.println(quizGame.getXP());
        quizGame.next();

        System.out.println(quizGame.showCurrentQuery());
        quizGame.hitAnswer("broncopolmonite");
        System.out.println(quizGame.getStatusScore());
        System.out.println(quizGame.getXP());
        quizGame.next();
        System.out.println(quizGame.showCurrentQuery());
    }
}

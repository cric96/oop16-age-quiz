package home.model.quiz;
import static org.junit.Assert.*;
import org.junit.Test;

import home.model.level.Level;
import home.model.query.Category;
/**
 *
 */
public class QuizTest {
    @Test
    public void test1() {
        final QuizGame quizGame = new QuizGameImpl(Category.LIBERAL_ARTS, Level.Building.createBuildingLevel());
        System.out.println(quizGame.showCurrentQuery());
        System.out.println(quizGame.getStatusScore());
        quizGame.hitAnswer("7");
        System.out.println(quizGame.getStatusScore());
        quizGame.next();

        System.out.println(quizGame.showCurrentQuery());
        quizGame.hitAnswer("6");
        System.out.println(quizGame.getStatusScore());
    }
}

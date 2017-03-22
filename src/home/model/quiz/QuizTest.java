package home.model.quiz;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 *
 */
public class QuizTest {
    @Test
    public void test1() {
        final QuizGame quizGame = new QuizGameImpl(Category.LIBERAL_ARTS, 1);
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

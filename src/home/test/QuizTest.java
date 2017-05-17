package home.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;

import home.model.level.Level;
import home.model.query.Category;
import home.model.quiz.QuizGame;
import home.model.quiz.QuizGameFactory;
/**
 *
 */
public class QuizTest {
    private static final int INITIAL_VALUE = 0;
    private static final String ANSWER = "I don't know why";
    /**
     * 
     */
    @Test
    public void simpleTest() {
        final QuizGame quizGame = QuizGameFactory.createQuizGameAdvanced(Category.LIBERAL_ARTS, Level.Building.createBuildingLevel());
        assertSame(quizGame.getXP(), INITIAL_VALUE);
        quizGame.getStatusScore().forEach((x, y) -> assertSame(y, INITIAL_VALUE));
        assertFalse(quizGame.isFinished());
        quizGame.showCurrentQuery();
        try {
            quizGame.isAnswerCorrect();
            fail();
        } catch (IllegalStateException e) {
            assertNotNull(e);
        }
        try {
            quizGame.hitAnswer(ANSWER);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
        final String answer = quizGame.showCurrentQuery().getAnswers().stream().findFirst().get();
        quizGame.hitAnswer(answer);
        //if the answer hitten is correct XP must be different from zero, otherwise it will be zero cause it can't go negative
        if (quizGame.isAnswerCorrect()) {
            assertNotSame(quizGame.getXP(), INITIAL_VALUE);
        } else {
            assertSame(quizGame.getXP(), INITIAL_VALUE);
        }
        quizGame.next();
        quizGame.getStatusScore().forEach((x, y) -> assertNotSame(y, INITIAL_VALUE));
        quizGame.setFinished();
        try {
            quizGame.next();
            fail();
        } catch (NoSuchElementException e) {
            assertNotNull(e);
        }
        try {
            quizGame.hitAnswer(answer);
            fail();
        } catch (NoSuchElementException e) {
            assertNotNull(e);
        }
        try {
            quizGame.showCurrentQuery();
            fail();
        } catch (NoSuchElementException e) {
            assertNotNull(e);
        }
        try {
            quizGame.isAnswerCorrect();
            fail();
        } catch (NoSuchElementException e) {
            assertNotNull(e);
        }
    }
}

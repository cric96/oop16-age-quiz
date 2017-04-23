package home.view.quiz;

import java.util.List;
import java.util.Map;

import home.controller.QuizController;
import home.view.View;
/**
 *
 */
public interface QuizView extends View<QuizController> {
    /**
     * this method shows the question of the current quiz.
     * @param question
     *          current question to be answered
     */
    void showQuestion(String question);
    /**
     * this method shows the answers of the current quiz.
     * @param answers
     *          possible answers for the question
     */
    void showAnswers(List<String> answers);
    /**
     * Shows time passing.
     * @param time
     *          current time
     */ 
    void showTime(int time);
    /**
     * when time elapsed it shows the final score and the exp earned.
     * @param exp
     *          exp earned, it could be either positive or negative
     * @param score
     *          final score
     */
    void showFinalScore(int exp, Map<String, Integer> score);
    /**
     * QuizController tells this method if the answer given was correct.
     * @param isAnswerCorrect
     *          true if the answer given was correct
     */
    void isCorrect(boolean isAnswerCorrect);
}

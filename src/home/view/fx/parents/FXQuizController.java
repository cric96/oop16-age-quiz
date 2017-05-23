package home.view.fx.parents;

import java.util.List;
import java.util.Map;

import home.controller.observer.QuizObserver;
import home.view.fx.FXMLController;

/**
 * controller of a quiz javafx view.
 */
public interface FXQuizController extends FXMLController {

    /**
     * 
     * @param question
     *      the question to show.
     */
    void setQuestion(String question);

    /**
     * 
     * @param time
     *      time for the quiz.
     */
    //using timeLine to allow smoothing progressBar
    void setTime(int time);

    /**
     * 
     * @param answers
     *      answers for the related question.
     */
    void setAnswers(List<String> answers);

    /**
     * 
     * @param qController
     *      the controller to be linked.
     */
    void setController(QuizObserver qController);

    /**
     * 
     * @param answer
     *      the answer to be checked.
     */
    void showIfIsCorrect(boolean answer);

    /**
     * 
     * @param exp
     *  exp earned in this quiz.
     * @param score
     *  status results for this quiz.
     */
    void end(int exp, Map<String, Integer> score);

}
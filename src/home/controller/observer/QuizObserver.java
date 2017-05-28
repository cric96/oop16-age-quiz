package home.controller.observer;
/**
 * the quiz observer.
 */
public interface QuizObserver extends Observer {
    /**
     * The QuizView calls this method and it has to verify if the answer is correct
     * it should go on with the quiz and let the model calculate the score.
     * @param answer
     *  the answer hitten
     */
    void hitAnswer(String answer);
    /**
     * going on the next question.
     */
    void next();
    /**
     * QuizView calls this method to return on the worldView.
     */
    void quizFinished();
}

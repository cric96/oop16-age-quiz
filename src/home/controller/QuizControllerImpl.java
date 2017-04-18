package home.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import home.model.level.Level;
import home.model.query.Category;
import home.model.quiz.QuizGame;
import home.model.quiz.QuizGameFactory;
import home.view.QuizView;

class QuizControllerImpl extends AbstractController<QuizView>implements QuizController {
    private QuizGame currentQuiz;
    QuizControllerImpl(final QuizView...views) {
        super(views);
        this.currentQuiz = QuizGameFactory.createQuizGameAdvanced(Category.LIBERAL_ARTS,
                                            Level.Building.createBuildingLevel());
    }
    private void updateQuery() {
        this.getInternalView().forEach(x -> x.showQuestion(this.currentQuiz.showCurrentQuery().getQuestion()));
        this.getInternalView().forEach(x -> x.showAnswers(new ArrayList<>(this.currentQuiz.showCurrentQuery().getAnswers())));
    }
    @Override
    public void checkUpdate() {
        this.currentQuiz = QuizGameFactory.createQuizGameAdvanced(Category.SCIENCE, Level.Building.createBuildingLevel());
        this.updateQuery();
        final QuizTimer qTimer = new QuizTimer(this.currentQuiz.getQuizDuration());
        qTimer.start();
    }

    @Override
    public void hitAnswer(final String answer) {
        this.currentQuiz.hitAnswer(answer);
        this.getInternalView().forEach(x -> x.isCorrect(this.currentQuiz.isAnswerCorrect()));
    }

    @Override
    public void quizFinished() {
        //TODO chiamare metodo changeDisplay su Container
    }
    @Override
    public void next() {
        this.currentQuiz.next();
        this.updateQuery();
    }

    @Override
    protected void attachViews() {
        this.getInternalView().forEach(x -> x.attachOn(this));
    }

    private class QuizTimer extends Thread {
        private static final int SECOND = 1000;
        private int time;
        QuizTimer(final int time) {
            this.time = time;
        }
        public void run() {
            while (this.time > 0) {
                QuizControllerImpl.this.getInternalView().forEach(x -> x.showTime(time));
                this.time--;
                try {
                    sleep(SECOND);
                } catch (Exception e) {
                    //TODO informare tutte le view
                }
            }
            final QuizGame quiz = QuizControllerImpl.this.currentQuiz;
            quiz.setFinished();
            QuizControllerImpl.this.getInternalView().forEach(x -> x.showFinalScore(quiz.getXP(), 
                                quiz.getStatusScore().entrySet().stream().collect(
                                        Collectors.toMap(y -> y.getKey().name(), y -> y.getValue()))));
        }
    }

    }

package home.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import home.controller.observer.QuizObserver;
import home.controller.profile.Profile;
import home.controller.profile.ProfileBox;
import home.model.Game;
import home.model.image.ImageComponent;
import home.model.image.ImageInfo;
import home.model.quiz.QuizGame;
import home.utility.ResourceManager;
import home.view.Container;
import home.view.View;
import home.view.ViewType;
import home.view.quiz.QuizView;

final class QuizObserverImpl extends AbstractObserver implements QuizObserver {
    private QuizGame currentQuiz;
    private static final String FINISH_ERROR = "The game is finished";
    private static final String IO_ERROR = "SOME ERROR DUE TO SAVE FILE";
    private final Set<QuizView> views;
    QuizObserverImpl(final Set<QuizView> views) {
        this.views = views;
        this.views.forEach(x -> x.attachOn(this));
    }
    private void updateQuery() {
        this.views.forEach(x -> x.showQuestion(this.currentQuiz.showCurrentQuery().getQuestion()));
        this.views.forEach(x -> x.showAnswers(new ArrayList<>(this.currentQuiz.showCurrentQuery().getAnswers())));
    }
    @Override
    protected void update() {
        this.currentQuiz = Game.getGame().getCurrentQuiz().orElseThrow(() -> new IllegalStateException());
        super.update();
        this.updateQuery();
        final QuizTimer qTimer = new QuizTimer(this.currentQuiz.getQuizDuration());
        qTimer.start();
    }
    @Override
    protected void defineUpdateView(final View<?> view) {
        final ImageInfo img = ImageComponent.createComponent(this.currentQuiz.getCategory().name());
        final QuizView quiz = (QuizView) view;
        quiz.showBackground(ResourceManager.load(img.getPath()));
    }
    @Override
    public void hitAnswer(final String answer) {
        this.currentQuiz.hitAnswer(answer);
        this.views.forEach(x -> x.isCorrect(this.currentQuiz.isAnswerCorrect()));
    }

    @Override
    public void quizFinished() {
        Game.getGame().endCurrentQuiz();
        final Profile selected = ProfileBox.getProfileBox().getSelected().orElseThrow(() -> new IllegalStateException());
        try {
            Game.getGame().save(selected.getSaveGame());
        } catch (IOException e) {
            super.showErrors(IO_ERROR);
        }
        Container.getContainer().changeDisplay(ViewType.WORLD);
    }
    @Override
    public void next() {
        try {
            this.currentQuiz.next();
            this.updateQuery();
        } catch (NoSuchElementException exc) {
            super.showErrors(FINISH_ERROR);
        }
    }

    private class QuizTimer extends Thread {
        private static final int SECOND = 1000;
        private static final String ERROR = "an error on thread occured";
        private int time;
        QuizTimer(final int time) {
            this.time = time;
        }
        public void run() {
            while (this.time >= 0) {
                QuizObserverImpl.this.views.forEach(x -> x.showTime(time));
                this.time--;
                try {
                    sleep(SECOND);
                } catch (Exception e) {
                    QuizObserverImpl.this.showErrors(ERROR);
                }
            }
            final QuizGame quiz = QuizObserverImpl.this.currentQuiz;
            quiz.setFinished();
            QuizObserverImpl.this.views.forEach(x -> x.showFinalScore(quiz.getXP(), 
                                quiz.getStatusScore().entrySet().stream().collect(
                                        Collectors.toMap(y -> y.getKey().name(), y -> y.getValue()))));
        }
    }

    @Override
    protected Set<? extends View<?>> getAttached() {
        return views;
    }
}

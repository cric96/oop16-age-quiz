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
import home.utility.BundleLanguageManager;
import home.utility.Bundles;
import home.utility.ResourceManager;
import home.view.Container;
import home.view.MessageType;
import home.view.View;
import home.view.ViewType;
import home.view.quiz.QuizView;

final class QuizObserverImpl extends AbstractObserver implements QuizObserver {
    private QuizGame currentQuiz;
    private static final String FINISH_ERROR = "FINISH_ERROR";
    private static final String IO_ERROR = "FILE_ERROR";
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
        this.updateQuery();
        super.update();
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
        final String error = BundleLanguageManager.get().getBundle(Bundles.ERROR).getString(IO_ERROR);
        final Profile selected = ProfileBox.getProfileBox().getSelected().orElseThrow(() -> new IllegalStateException());
        try {
            Game.getGame().save(selected.getSaveGame());
        } catch (IOException e) {
            this.showMessageInViews(error, MessageType.ERROR);
        }
        Container.getContainer().changeDisplay(ViewType.WORLD);
    }
    @Override
    public void next() {
        final String error = BundleLanguageManager.get().getBundle(Bundles.ERROR).getString(FINISH_ERROR);
        try {
            this.currentQuiz.next();
            this.updateQuery();
        } catch (NoSuchElementException exc) {
            this.showMessageInViews(error, MessageType.ERROR);
        }
    }

    private class QuizTimer extends Thread {
        private static final int SECOND = 1000;
        private static final String ERROR = "GENERIC_ERROR";
        private int time;
        QuizTimer(final int time) {
            this.time = time;
        }
        public void run() {
            final String error = BundleLanguageManager.get().getBundle(Bundles.ERROR).getString(ERROR);
            while (this.time >= 0) {
                QuizObserverImpl.this.views.forEach(x -> x.showTime(time));
                this.time--;
                try {
                    sleep(SECOND);
                } catch (Exception e) {
                    QuizObserverImpl.this.showMessageInViews(error, MessageType.ERROR);
                }
            }
            final QuizGame quiz = QuizObserverImpl.this.currentQuiz;
            quiz.setFinished();
            QuizObserverImpl.this.views.forEach(x -> x.showFinalScore(quiz.getXP(), 
                                quiz.getStatusScore().entrySet().stream().collect(
                                        Collectors.toMap(y -> y.getKey().toString(), y -> y.getValue()))));
        }
    }

    @Override
    protected Set<? extends View<?>> getAttached() {
        return views;
    }
}

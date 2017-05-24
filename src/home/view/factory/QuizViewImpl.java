package home.view.factory;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import home.controller.observer.QuizObserver;
import home.view.MessageType;
import home.view.fx.FXMLFiles;
import home.view.fx.FxmlResourceManager;
import home.view.fx.parent.FXQuizController;
import home.view.quiz.QuizView;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
//package-protected
/**
 * Implementation of QuizView.
 */
class QuizViewImpl extends AbstractFXView<Parent> implements QuizView {
    private final FXQuizController fxController;
    private final AnchorPane parent;
    QuizViewImpl() {
        this.fxController = new FXQuizControllerImpl();
        final FxmlResourceManager fxmlManager = new FxmlResourceManager(FXMLFiles.QUIZ, this.fxController);
        this.parent = (AnchorPane) fxmlManager.load();
        this.setParent(parent);
    }
    @Override
    public void showQuestion(final String question) {
        Objects.requireNonNull(question);
        this.fxController.setQuestion(question);

    }

    @Override
    public void showAnswers(final List<String> answers) {
        Objects.requireNonNull(answers);
        this.fxController.setAnswers(answers);
    }

    @Override
    public void showTime(final int time) {
        if (time < 0) {
            throw new IllegalArgumentException("Cannot solve negative time");
        }
        this.fxController.setTime(time);

    }

    @Override
    public void showFinalScore(final int exp, final Map<String, Integer> score) {
        Objects.requireNonNull(score);
        Platform.runLater(() -> {
            this.fxController.end(exp, score);
        });
    }

    @Override
    public void isCorrect(final boolean isAnswerCorrect) {
        this.fxController.showIfIsCorrect(isAnswerCorrect);
    }

    @Override
    public void show() { }
    @Override
    protected void onClickMessage(final MessageType type, final Optional<ButtonType> button) { }
    @Override
    public void attachOn(final QuizObserver controller) {
        this.fxController.setController(controller);
    }
    @Override
    public void showBackground(final URL image) {
        final Image img = new Image(image.toExternalForm());
        final BackgroundImage background = new BackgroundImage(img, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, 
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        this.parent.setBackground(new Background(background));
    }
}

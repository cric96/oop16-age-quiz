package home.view.quiz;

import java.util.List;
import java.util.Optional;

import home.controller.observer.QuizObserver;
import home.view.fx.CSSManager;
import home.view.fx.FXMLController;
import home.view.fx.StyleSheet;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
//package-protected
final class FXQuizController implements FXMLController {
    private static final int TIME_TO_CHANGE = 500;
    private static final double WARNING_PERCENTAGE = 0.2; 
    private int startTime;
    private QuizObserver qController;
    @FXML
    private Label question;
    @FXML
    private ProgressBar time;
    @FXML
    private VBox answers;
    /**
     * 
     * @param question
     *      the question to show.
     */
    public void setQuestion(final String question) {
        Platform.runLater(() -> this.question.setText(question));
    }
    @FXML
    private void initialize() { //NOPMD - private metod called by itself when fxml file is load.
        CSSManager.addStyleSheet(StyleSheet.ANSWERS, this.answers);
        CSSManager.addStyleSheet(StyleSheet.QUESTION, this.question);
        CSSManager.addStyleClass("my-label-default", this.question);
        CSSManager.addStyleSheet(StyleSheet.PROGRESS_BAR, this.time);
        CSSManager.addStyleClass("my-progressBar", this.time);
    }
    /**
     * 
     * @param time
     *      time for the quiz.
     */
    //using timeLine to allow smoothing progressBar
    public void setTime(final int time) {
        if (this.startTime == 0) {
            this.startTime = time;
            this.time.setProgress(1.0);
            final Timeline timeline = new Timeline();
            final KeyValue keyValue = new KeyValue(this.time.progressProperty(), 0);
            final KeyFrame keyFrame = new KeyFrame(new Duration(startTime * 1000), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
        }
        if (time <= this.startTime * WARNING_PERCENTAGE) {
            this.setIntermittence(time);
        }
        if (time == 0) {
            this.startTime = 0;
            CSSManager.addStyleClass("my-progressBar", this.time);
        }
    }
    private void setIntermittence(final int time) {
        if (time % 2 == 0) {
            CSSManager.addStyleClass("my-progressBar-intermittence", this.time);
        } else {
            CSSManager.addStyleClass("my-progressBar-warning", this.time);
        }
    }
    /**
     * 
     * @param answers
     *      answers for the related question.
     */
    public void setAnswers(final List<String> answers) {
        Platform.runLater(() -> {
            this.answers.getChildren().clear();
            answers.forEach(y -> {
               final Button ans = new Button(y);
               CSSManager.addStyleClass("my-button", ans);
               this.answers.getChildren().add(ans);
               ans.setMaxWidth(Double.MAX_VALUE);
               ans.setOnAction(e -> {
                   Optional.ofNullable(this.qController).orElseThrow(() -> new IllegalStateException()).hitAnswer(y);
               });
            });
        });
    }
    /**
     * 
     * @param qController
     *      the controller to be linked.
     */
    public void setController(final QuizObserver qController) {
        this.qController = qController;
    }
    /**
     * 
     * @param answer
     *      the answer to be checked.
     */
    public void showIfIsCorrect(final boolean answer) {
        Platform.runLater(() -> {
            CSSManager.addStyleClass(answer ? "my-label-correct" : "my-label-wrong", this.question);
        });
        final Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                answers.setDisable(true);
                Thread.sleep(TIME_TO_CHANGE);
                return null;
            }
        };
        sleeper.setOnSucceeded((e) -> {
            answers.setDisable(false);
            Optional.ofNullable(this.qController).orElseThrow(() -> new IllegalStateException()).next();
            CSSManager.addStyleClass("my-label-default", this.question);
        });
        new Thread(sleeper).start();
    }
}

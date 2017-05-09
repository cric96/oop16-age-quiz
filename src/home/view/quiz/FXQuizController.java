package home.view.quiz;

import java.util.List;
import java.util.Optional;
import home.controller.QuizController;
import home.utility.ResourceManager;
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
final class FXQuizController {
    private static final int TIME_TO_CHANGE = 500;
    private static final double WARNING_PERCENTAGE = 0.2; 
    private int startTime;
    private QuizController qController;
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
    private void initialize() {
        this.answers.getStylesheets().add(ResourceManager.load("/style/answers.css").toExternalForm());
        this.question.getStylesheets().add(ResourceManager.load("/style/question.css").toExternalForm());
        this.question.getStyleClass().add("my-label-default");
        this.time.getStylesheets().add(ResourceManager.load("/style/progressBar.css").toExternalForm());
        this.time.getStyleClass().add("my-progressBar");
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
            this.time.getStyleClass().clear();
            this.time.getStyleClass().add("my-progressBar");
        }
    }
    private void setIntermittence(final int time) {
        if (time % 2 == 1) {
            this.time.getStyleClass().clear();
            this.time.getStyleClass().add("my-progressBar-intermittence");
        } else {
            this.time.getStyleClass().clear();
            this.time.getStyleClass().add("my-progressBar-warning");
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
                ans.getStyleClass().add("my-button");
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
    public void setController(final QuizController qController) {
        this.qController = qController;
    }
    /**
     * 
     * @param answer
     *      the answer to be checked.
     */
    public void showIfIsCorrect(final boolean answer) {
        Platform.runLater(() -> {
            this.question.getStyleClass().clear();
            this.question.getStyleClass().add(answer ? "my-label-correct" : "my-label-wrong");
        });
        final Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                Thread.sleep(TIME_TO_CHANGE);
                return null;
            }
        };
        sleeper.setOnSucceeded((e) -> {
            Optional.ofNullable(this.qController).orElseThrow(() -> new IllegalStateException()).next();
            this.question.getStyleClass().clear();
            this.question.getStyleClass().add("my-label-default");
        });
        new Thread(sleeper).start();
    }
}

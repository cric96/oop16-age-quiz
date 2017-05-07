package home.view.quiz;

import java.util.List;
import java.util.Optional;

import com.sun.prism.paint.Color;

import home.controller.QuizController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
public final class FXQuizController {
    private static final int TIME_TO_CHANGE = 500;
    private int startTime;
    private QuizController qController;
    @FXML
    private Label question;
    @FXML
    private ProgressBar time;
    @FXML
    private VBox answers;
    public void setQuestion(final String question) {
        Platform.runLater(() -> this.question.setText(question));
    }
    public void setTime(final int time) {
        if (this.startTime == 0) {
            this.startTime = time;
        }
        Platform.runLater(() -> this.time.setProgress((double) time / (double) this.startTime));
        if (time == 0) {
            this.startTime = 0;
        }
    }
    public void setAnswers(final List<String> answers) {
        Platform.runLater(() -> {
            this.answers.getChildren().clear();
            answers.forEach(y -> {
                final Button ans = new Button(y);
               this.answers.getChildren().add(ans);
               ans.setMaxWidth(Double.MAX_VALUE);
               ans.setOnAction(e -> {
                   Optional.ofNullable(this.qController).orElseThrow(() -> new IllegalStateException()).hitAnswer(y);
               });
            });
        });
    }
    public void setController(final QuizController qController) {
        this.qController = qController;
    }
    public void showIfIsCorrect(final boolean answer) {
        Platform.runLater(() -> {
            this.question.setStyle("-fx-background-color: " + (answer ? "green" : "red"));
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
            this.question.setStyle("");
        });
        new Thread(sleeper).start();
    }
}

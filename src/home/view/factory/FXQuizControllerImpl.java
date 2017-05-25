package home.view.factory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import home.controller.observer.QuizObserver;
import home.utility.BundleLanguageManager;
import home.utility.Bundles;
import home.utility.view.UtilityScreen;
import home.view.fx.CSSManager;
import home.view.fx.StyleSheet;
import home.view.fx.parent.FXQuizController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
//package-protected
final class FXQuizControllerImpl implements FXQuizController {
    private static final int TIME_TO_CHANGE = 500;
    private static final double WARNING_PERCENTAGE = 0.2; 
    private static final double MARGIN = UtilityScreen.getScreenWidth() / 5;
    private int startTime;
    private QuizObserver qController;
    @FXML
    private Label question;
    @FXML
    private ProgressBar time;
    @FXML
    private VBox answers;

    @Override
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

    //using timeLine to allow smoothing progressBar
    @Override
    public void setTime(final int time) {
        if (this.startTime == 0) {
            //i must remove the event that call end quiz
            this.answers.setOnMouseClicked(e -> { });
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

    @Override
    public void setAnswers(final List<String> answers) {
        Collections.shuffle(answers);
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

    @Override
    public void end(final int exp, final Map<String, Integer> score) {
        this.question.setText(BundleLanguageManager.get().getBundle(Bundles.LABEL).getString("QUIZFINISHED"));
        final VBox results = new VBox();
        VBox.setMargin(results, new Insets(0, MARGIN, 0, MARGIN));
        CSSManager.addStyleClass("my-vbox", results);
        this.answers.getChildren().clear();
        final String expLang = BundleLanguageManager.get().getBundle(Bundles.LABEL).getString("EXP");
        final Label experience = new Label(expLang + ": " + exp);
        CSSManager.addStyleClass("my-label", experience);
        results.getChildren().add(experience);
        for (final Map.Entry<String, Integer> value : score.entrySet()) {
            final Label statusScore = new Label(value.getKey() + ": " + value.getValue() + "\n");
            CSSManager.addStyleClass("my-label", statusScore);
            results.getChildren().add(statusScore);
        }
        final Label goBack = new Label(BundleLanguageManager.get().getBundle(Bundles.LABEL).getString("GOBACK"));
        CSSManager.addStyleClass("my-goBack", goBack);
        this.answers.setOnMouseClicked(e -> this.qController.quizFinished());
        this.answers.getChildren().add(results);
        this.answers.getChildren().add(goBack);
    }

    @Override
    public void setController(final QuizObserver qController) {
        this.qController = qController;
    }

    @Override
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
            //this check is to avoid problems when the user click on an answer after the timeout and before the screen update
            if (this.startTime != 0) {
                Optional.ofNullable(this.qController).orElseThrow(() -> new IllegalStateException()).next();
            }
            CSSManager.addStyleClass("my-label-default", this.question);
        });
        new Thread(sleeper).start();
    }
}

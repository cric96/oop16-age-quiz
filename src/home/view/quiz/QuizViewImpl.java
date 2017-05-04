package home.view.quiz;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sun.glass.ui.PlatformFactory;

import home.controller.QuizController;
import home.utility.ResourceManager;
import home.view.MessageType;
import home.view.fx.AbstractFXView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//package-protected
public class QuizViewImpl extends AbstractFXView implements QuizView {
    private static final String FXMLFile = "FXMLquizView.fxml";
    private QuizController qController;
    private final FXQuizController fxController;
    public QuizViewImpl() throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(QuizViewImpl.class.getResource(FXMLFile));
        final Parent parent = loader.load();
        this.fxController =  loader.getController();
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
        Platform.runLater(() -> {
            final Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(this.getParent().getScene().getWindow());
            alert.setTitle("");
            String results = "";
            for (final Map.Entry<String, Integer> value : score.entrySet()) {
                results += value.getKey() + ": " + value.getValue() + "\n";
            }
            alert.setHeaderText("Experience earned: " + exp + "\n" + results); 
            alert.showAndWait();
            this.qController.quizFinished();
        });
    }

    @Override
    public void isCorrect(final boolean isAnswerCorrect) {
        this.fxController.showIfIsCorrect(isAnswerCorrect);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
    }
    @Override
    protected void onClickMessage(final MessageType type, final Optional<ButtonType> button) {
        // TODO Auto-generated method stub
    }
    @Override
    public void attachOn(final QuizController controller) {
        this.qController = controller;
        this.fxController.setController(controller);
    }
}

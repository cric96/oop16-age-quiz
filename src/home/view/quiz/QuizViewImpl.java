package home.view.quiz;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import home.controller.QuizController;
import home.view.MessageType;
import home.view.fx.AbstractFXView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
//package-protected
public class QuizViewImpl extends AbstractFXView implements QuizView {
    private static final String FXML_FILE = "FXMLquizView.fxml";
    private QuizController qController;
    private final FXQuizController fxController;
    private final AnchorPane parent;
    public QuizViewImpl() throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(QuizViewImpl.class.getResource(FXML_FILE));
        this.parent = loader.load();
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
    public void show() { }
    @Override
    protected void onClickMessage(final MessageType type, final Optional<ButtonType> button) { }
    @Override
    public void attachOn(final QuizController controller) {
        this.qController = controller;
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
